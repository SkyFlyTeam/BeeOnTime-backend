package com.fatec.ms_usuario.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class UsuarioDTO {
    
    private Long empCod;

    private LocalTime horarioAlmoco; // Horário do almoço
    private LocalTime horarioEntrada; // Horário de entrada
    private LocalTime horarioSaida; // Horário de saída

    private Boolean horarioFlexivel;
    private Long nivelAcesso_cod;
    private Long setorCod;
    private String usuarioEmail;
    private String usuarioTipoContratacao;
    private LocalDate usuario_DataNascimento;
    private Integer usuario_cargaHoraria;
    private String usuario_cargo;
    private String usuario_cpf;
    private LocalDate usuario_dataContratacao;
    private String usuario_nome;
    private String usuario_nrRegistro; // Alterado para String
    private String usuario_senha;
    private List<Boolean> diasSemana;

    // Getters and Setters
    public Long getEmpCod() {
        return empCod;
    }

    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    }

    public LocalTime getHorarioAlmoco() {
        return horarioAlmoco;
    }

    public void setHorarioAlmoco(LocalTime horarioAlmoco) {
        this.horarioAlmoco = horarioAlmoco;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public Boolean getHorarioFlexivel() {
        return horarioFlexivel;
    }

    public void setHorarioFlexivel(Boolean horarioFlexivel) {
        this.horarioFlexivel = horarioFlexivel;
    }

    public Long getNivelAcesso_cod() {
        return nivelAcesso_cod;
    }

    public void setNivelAcesso_cod(Long nivelAcesso_cod) {
        this.nivelAcesso_cod = nivelAcesso_cod;
    }

    public Long getSetorCod() {
        return setorCod;
    }

    public void setSetorCod(Long setorCod) {
        this.setorCod = setorCod;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioTipoContratacao() {
        return usuarioTipoContratacao;
    }

    public void setUsuarioTipoContratacao(String usuarioTipoContratacao) {
        this.usuarioTipoContratacao = usuarioTipoContratacao;
    }

    public LocalDate getUsuario_DataNascimento() {
        return usuario_DataNascimento;
    }

    public void setUsuario_DataNascimento(LocalDate usuario_DataNascimento) {
        this.usuario_DataNascimento = usuario_DataNascimento;
    }

    public Integer getUsuario_cargaHoraria() {
        return usuario_cargaHoraria;
    }

    public void setUsuario_cargaHoraria(Integer usuario_cargaHoraria) {
        this.usuario_cargaHoraria = usuario_cargaHoraria;
    }

    public String getUsuario_cargo() {
        return usuario_cargo;
    }

    public void setUsuario_cargo(String usuario_cargo) {
        this.usuario_cargo = usuario_cargo;
    }

    public String getUsuario_cpf() {
        return usuario_cpf;
    }

    public void setUsuario_cpf(String usuario_cpf) {
        this.usuario_cpf = usuario_cpf;
    }

    public LocalDate getUsuario_dataContratacao() {
        return usuario_dataContratacao;
    }

    public void setUsuario_dataContratacao(LocalDate usuario_dataContratacao) {
        this.usuario_dataContratacao = usuario_dataContratacao;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public String getUsuario_nrRegistro() {
        return usuario_nrRegistro;
    }

    public void setUsuario_nrRegistro(String usuario_nrRegistro) { // Alterado para String
        this.usuario_nrRegistro = usuario_nrRegistro;
    }

    public String getUsuario_senha() {
        return usuario_senha;
    }

    public void setUsuario_senha(String usuario_senha) {
        this.usuario_senha = usuario_senha;
    }

    public List<Boolean> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<Boolean> diasSemana) {
        this.diasSemana = diasSemana;
    }
}

