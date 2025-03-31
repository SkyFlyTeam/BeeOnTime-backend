package com.msponto.ms_ponto.entidade.mongo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.msponto.ms_ponto.enums.TipoPonto;

@Document(collection = "MarcacaoPontos")
public class MarcacaoPontos {
    @Id
    private String id;
    private Long usuarioCod;
    private Long horasCod;
    private LocalDate data;
    private List<Ponto> pontos;

    public static class Ponto{
        private LocalTime horario;
        private TipoPonto tipo;

        // ENTRADA(0),
        // SAIDA(1),
        // ALMOCO(2);

        public Ponto(LocalTime horario, TipoPonto tipo){
            this.horario = horario;
            this.tipo = tipo;
        }

        public TipoPonto getTipoPonto(){
            return tipo;
        }

        public void setTipoPonto(TipoPonto tipo){
            this.tipo = tipo;
        }

        public LocalTime getHorarioPonto(){
            return this.horario;
        }

        public void setHorarioPonto(LocalTime horario){
            this.horario = horario;
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUsuarioCod() {
        return this.usuarioCod;
    }

    public void setUsuarioCod(Long usuarioCod) {
        this.usuarioCod = usuarioCod;
    }
    
    public Long getHorasCod() {
        return this.horasCod;
    }

    public void setHorasCod(Long horasCod) {
        this.horasCod = horasCod;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Ponto> getPontos() {
		return this.pontos;
	}
	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

}
