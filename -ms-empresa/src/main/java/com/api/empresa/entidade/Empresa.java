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
    private long emp_cod;

    @Column
    private String emp_nome;

    @Column
    private String emp_cnpj;

    @Column
    private String emp_razaoSocial;

    @Column
    private String emp_cep;

    @Column
    private String emp_cidade;

    @Column
    private String emp_estado;

    @Column
    private String emp_endereco;

    // Getters and Setters

    public long getEmp_cod() {
        return emp_cod;
    }

    public void setEmp_cod(long emp_cod) {
        this.emp_cod = emp_cod;
    }

    public String getEmp_nome() {
        return emp_nome;
    }

    public void setEmp_nome(String emp_nome) {
        this.emp_nome = emp_nome;
    }

    public String getEmp_cnpj() {
        return emp_cnpj;
    }

    public void setEmp_cnpj(String emp_cnpj) {
        if (emp_cnpj == null || emp_cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
        }
        this.emp_cnpj = emp_cnpj;
    }

    public String getEmp_razaoSocial() {
        return emp_razaoSocial;
    }

    public void setEmp_razaoSocial(String emp_razaoSocial) {
        this.emp_razaoSocial = emp_razaoSocial;
    }

    public String getEmp_cep() {
        return emp_cep;
    }

    public void setEmp_cep(String emp_cep) {
        if (emp_cep == null || emp_cep.length() != 8) {
            throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
        }
        this.emp_cep = emp_cep;
    }

    public String getEmp_cidade() {
        return emp_cidade;
    }

    public void setEmp_cidade(String emp_cidade) {
        this.emp_cidade = emp_cidade;
    }

    public String getEmp_estado() {
        return emp_estado;
    }

    public void setEmp_estado(String emp_estado) {
        this.emp_estado = emp_estado;
    }

    public String getEmp_endereco() {
        return emp_endereco;
    }

    public void setEmp_endereco(String emp_endereco) {
        this.emp_endereco = emp_endereco;
    }
}