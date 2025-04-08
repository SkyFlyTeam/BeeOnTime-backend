package com.fatec.ms_usuario.entidade;

import java.util.List;

import com.fatec.ms_usuario.entidade.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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

    public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	@Column
    private String empCep;

    @Column
    private String empCidade;

    @Column
    private String empEstado;

    @Column
    private String empEndereco;

    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "empresa")
    private List<Setor> setores;

    // Getters and Setters

    public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

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