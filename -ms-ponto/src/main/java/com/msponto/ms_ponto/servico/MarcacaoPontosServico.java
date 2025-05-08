package com.msponto.ms_ponto.servico;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.dto.JornadaDTO;
import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos.Ponto;
import com.msponto.ms_ponto.entidade.mysql.Atraso;
import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.enums.TipoPonto;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;
import com.msponto.ms_ponto.repositorio.mongo.MarcacaoPontosRepositorio;
import com.msponto.ms_ponto.repositorio.mysql.AtrasoRepositorio;
import com.msponto.ms_ponto.repositorio.mysql.HorasRepositorio;

@Service
public class MarcacaoPontosServico {

    @Autowired
    private MarcacaoPontosRepositorio mponto_repo;
    
    @Autowired
    private AtrasoRepositorio atrasoRepositorio;
    
    @Autowired
    private HorasRepositorio horasRepositorio;

    @Autowired
    HorasServico horas_servico;
    
    @Autowired
    private UsuarioClient usuarioClient;

    public List<MarcacaoPontos> getPontosUsuario(Long usuario_cod) {
        List<MarcacaoPontos> pontos = mponto_repo.findByUsuarioCod(usuario_cod);
        return pontos;
    }

    public List<MarcacaoPontos> getPontosUsuarioByPeriod(Long mpontos_cod, LocalDate start_date, LocalDate end_date){
        List<MarcacaoPontos> mpontos = mponto_repo.findByUsuarioCodAndDataBetween(mpontos_cod, start_date, end_date);
        return mpontos;
    }

    public Optional<MarcacaoPontos> getPontosUsuarioByCodHoras(Long horasCod){
        Optional<MarcacaoPontos> mpontos = mponto_repo.findByHorasCod(horasCod);
        return mpontos;
    }

    public Boolean baterPonto(MarcacaoPontos mponto) {
        try {
            Optional<Horas> horas_existente = horas_servico.getHorasByCod(mponto.getHorasCod());
        
            if(horas_existente.isPresent()){
                Horas horas = horas_existente.get();
                verificarAtraso(mponto, horas);
                
                Optional<MarcacaoPontos> mpontos_existente = mponto_repo.findByHorasCod(horas.getHorasCod());
                // Marcação de ponto já existe, só irei adicionar
                if(mpontos_existente.isPresent()){
                    MarcacaoPontos mponto_existe = mpontos_existente.get();
                    mponto_existe.getPontos().add(mponto.getPontos().getFirst());
                    mponto_repo.save(mponto_existe);

                    // Atualizando a quantidade de horas 
                    List<Ponto> pontos = mponto_existe.getPontos();
        
                    if (pontos.size() <= 1) {
                        System.out.println("[ERRO]: Não há pontos o suficiente para calcular as horas");
                        return true;
                    }

                    Boolean horas_atualizadas = horas_servico.calculatingHours(mponto_existe);

                    if(!horas_atualizadas){
                        System.err.println("[ERRO]: Falha ao tentar atualizar as horas");
                        return false;
                    }

                    return true;
                }else{
                    mponto.setData(LocalDate.now());
                    mponto_repo.save(mponto);
                }
                return true;
            } 
            return false;
        } catch (Exception e) {
            System.err.println("[ERRO]: " + e);
            return false;
        }
    }
    
    public Boolean updateMponto(MarcacaoPontos mpontos_att) {
        try {
            Optional<MarcacaoPontos> mponto_existente = mponto_repo.findById(mpontos_att.getId());
            if (mponto_existente.isPresent()) {
                MarcacaoPontos mponto = mponto_existente.get();
                
                mponto.setPontos(mpontos_att.getPontos());
                
                mponto_repo.save(mponto);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("[ERRO]: " + e);
            return false;
        }
    }    
    
    private void verificarAtraso(MarcacaoPontos mponto, Horas horas) {
        if (mponto.getPontos() != null && !mponto.getPontos().isEmpty()) {

            for (Ponto ponto : mponto.getPontos()) {
                if (TipoPonto.ENTRADA.equals(ponto.getTipoPonto())) {

                    JornadaDTO jornada = usuarioClient.getJornadaByUsuario(mponto.getUsuarioCod());
                    LocalTime localTimeEntrada = jornada.getJornada_horarioEntrada() != null ? 
                                                  jornada.getJornada_horarioEntrada().toLocalTime() : null;

                    if (jornada.getJornada_horarioFlexivel()) {
                        // Para jornada flexível, calcula o horário limite
                        LocalTime horaLimiteEntrada = calcularHoraLimiteFlexivel(jornada.getUsuario_cod());

                        System.out.println("Hora limite de entrada (flexível): " + horaLimiteEntrada);
                        System.out.println("Hora de batida de ponto: " + ponto.getHorarioPonto());

                        // Calculando a diferença entre os horários
                        Duration duracao = Duration.between(horaLimiteEntrada, ponto.getHorarioPonto());
                        long atrasoEmMinutos = duracao.toMinutes();

                        // Convertendo o atraso para horas
                        float atrasoTempo = (float) atrasoEmMinutos / 60;
                        System.out.println("Atraso em horas (antes de arredondar): " + atrasoTempo);

                        // Arredondando o tempo para 2 casas decimais
                        atrasoTempo = Math.round(atrasoTempo * 100.0) / 100.0f;
                        System.out.println("Atraso em horas (após arredondar): " + atrasoTempo);

                        // Verificando se o atraso é positivo antes de registrar
                        if (atrasoTempo > 0) {
                            Optional<Atraso> atrasoExistente = atrasoRepositorio.findByHoras_HorasCod(horas.getHorasCod());

                            if (atrasoExistente.isPresent()) {
                                // Se já existir um atraso, vamos atualizar
                                Atraso atraso = atrasoExistente.get();
                                atraso.setAtrasoTempo(atrasoTempo); // Atualiza o tempo do atraso
                                atraso.setHoras(horas); // Atualiza a associação com horas
                                atrasoRepositorio.save(atraso); // Salva o atraso atualizado
                                horas.setAtraso(atraso);
                                horasRepositorio.save(horas); // Atualiza a referência de atraso em horas
                            } else {
                                // Se não existir um atraso, criamos um novo
                                Atraso novoAtraso = new Atraso();
                                novoAtraso.setAtrasoTempo(atrasoTempo);
                                novoAtraso.setHoras(horas);

                                atrasoRepositorio.save(novoAtraso); // Salva o novo atraso
                                horas.setAtraso(novoAtraso); // Associa o novo atraso com as horas
                                horasRepositorio.save(horas); // Salva as horas com o novo atraso
                            }
                        } else {
                            System.out.println("O usuário está adiantado, nenhum atraso será registrado.");
                        }

                    } else {
                        // Se a jornada não for flexível, usaremos o horário fixo
                        if (localTimeEntrada != null && localTimeEntrada.isBefore(ponto.getHorarioPonto())) {
                            Duration duracao = Duration.between(localTimeEntrada, ponto.getHorarioPonto());

                            long atrasoEmMinutos = duracao.toMinutes();
                            System.out.println("Atraso em minutos (duração entre os horários): " + atrasoEmMinutos);

                            // Convertendo o atraso para horas
                            float atrasoTempo = (float) atrasoEmMinutos / 60;
                            System.out.println("Atraso em horas (antes de arredondar): " + atrasoTempo);

                            // Arredondando o tempo para 2 casas decimais
                            atrasoTempo = Math.round(atrasoTempo * 100.0) / 100.0f;

                            System.out.println("Atraso em horas (após arredondar): " + atrasoTempo);

                            // Verificando se o atraso é positivo antes de registrar
                            if (atrasoTempo > 0) {
                                Optional<Atraso> atrasoExistente = atrasoRepositorio.findByHoras_HorasCod(horas.getHorasCod());

                                if (atrasoExistente.isPresent()) {
                                    // Se já existir um atraso, vamos atualizar
                                    Atraso atraso = atrasoExistente.get();
                                    atraso.setAtrasoTempo(atrasoTempo); // Atualiza o tempo do atraso
                                    atraso.setHoras(horas); // Atualiza a associação com horas
                                    atrasoRepositorio.save(atraso); // Salva o atraso atualizado
                                    horas.setAtraso(atraso);
                                    horasRepositorio.save(horas); // Atualiza a referência de atraso em horas
                                } else {
                                    // Se não existir um atraso, criamos um novo
                                    Atraso novoAtraso = new Atraso();
                                    novoAtraso.setAtrasoTempo(atrasoTempo);
                                    novoAtraso.setHoras(horas);

                                    atrasoRepositorio.save(novoAtraso); // Salva o novo atraso
                                    horas.setAtraso(novoAtraso); // Associa o novo atraso com as horas
                                    horasRepositorio.save(horas); // Salva as horas com o novo atraso
                                }
                            } else {
                                System.out.println("O usuário está adiantado, nenhum atraso será registrado.");
                            }
                        }
                    }
                }
            }
        }
    }



    private LocalTime calcularHoraLimiteFlexivel(Long usuarioCod) {
        UsuarioDTO usuarioDTO = usuarioClient.getUsuarioByCod(usuarioCod);
        Integer cargaHoraria = usuarioDTO.getUsuario_cargaHoraria(); 

        // Converter para minutos
        int cargaHorariaEmMinutos = cargaHoraria * 60;

        // Hora limite de entrada: 21:59 - carga horária
        LocalTime horaLimite = LocalTime.of(21, 59);

        // Subtraindo a carga horária (em minutos) de 21:59
        LocalTime horaLimiteEntrada = horaLimite.minusMinutes(cargaHorariaEmMinutos + 60);

        System.out.println("Hora limite de entrada para jornada flexível (ajustada): " + horaLimiteEntrada);

        return horaLimiteEntrada;
    }




}
