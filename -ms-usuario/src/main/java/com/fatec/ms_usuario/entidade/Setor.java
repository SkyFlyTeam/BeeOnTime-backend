package com.fatec.ms_usuario.entidade;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long setorCod;

    @Column
    private String setorNome;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empCod", referencedColumnName = "empCod", insertable = false, updatable = false)
	@JsonIgnore
	private Empresa empresa;

	private Long empCod; // Foreign key field for Empresa	

    @OneToMany(mappedBy = "setor")
    private List<Usuario> usuarios;

    // Getters and Setters

    public long getSetorCod() {
        return setorCod;
    }

    public void setSetorCod(long setorCod) {
        this.setorCod = setorCod;
    }

    public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getEmpCod() {
		return empCod;
	}

	public void setEmpCod(Long empCod) {
		this.empCod = empCod;
	}

	public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }
}