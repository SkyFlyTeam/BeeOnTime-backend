package com.api.empresa.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empCod;

    @Column
    private String empNome;

    @Column
    private String empCnpj;

    @Column
    private String empRazaoSocial;

    @Column
    private String empCep;

    @Column
    private String empCidade;

    @Column
    private String empEstado;

    @Column
    private String empEndereco;

    // Getters and Setters

    public long getEmpCod() {
        return empCod;
    }

    public void setEmpCod(long empCod) {
        this.empCod = empCod;
    }

    public String getEmpNome() {
        return empNome;
    }

    public void setEmpNome(String empNome) {
        this.empNome = empNome;
    }

    public String getEmpCnpj() {
        return empCnpj;
    }

    public void setEmpCnpj(String empCnpj) {
        if (empCnpj == null || empCnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
        }
        this.empCnpj = empCnpj;
    }

    public String getEmpRazaoSocial() {
        return empRazaoSocial;
    }

    public void setEmpRazaoSocial(String empRazaoSocial) {
        this.empRazaoSocial = empRazaoSocial;
    }

    public String getEmpCep() {
        return empCep;
    }

    public void setEmpCep(String empCep) {
        if (empCep == null || empCep.length() != 8) {
            throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
        }
        this.empCep = empCep;
    }

    public String getEmpCidade() {
        return empCidade;
    }

    public void setEmpCidade(String empCidade) {
        this.empCidade = empCidade;
    }

    public String getEmpEstado() {
        return empEstado;
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado = empEstado;
    }

    public String getEmpEndereco() {
        return empEndereco;
    }

    public void setEmpEndereco(String empEndereco) {
        this.empEndereco = empEndereco;
    }
}