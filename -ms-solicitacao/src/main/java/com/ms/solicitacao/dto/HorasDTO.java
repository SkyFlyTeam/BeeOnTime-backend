package com.ms.solicitacao.dto;

import java.time.LocalDate;


public class HorasDTO {
    private Long horasCod;
    private Double horasExtras;
    private Float horasTrabalhadas;
    private Float horasNoturnas;
    private Float horasFaltantes;
    private LocalDate horasData;
    private Long usuarioCod;
    
	public Long getHorasCod() {
		return horasCod;
	}
	public void setHorasCod(Long horasCod) {
		this.horasCod = horasCod;
	}
	public Double getHorasExtras() {
		return horasExtras;
	}
	public void setHorasExtras(Double horasExtras) {
		this.horasExtras = horasExtras;
	}
	public Float getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(Float horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	public Float getHorasNoturnas() {
		return horasNoturnas;
	}
	public void setHorasNoturnas(Float horasNoturnas) {
		this.horasNoturnas = horasNoturnas;
	}
	public Float getHorasFaltantes() {
		return horasFaltantes;
	}
	public void setHorasFaltantes(Float horasFaltantes) {
		this.horasFaltantes = horasFaltantes;
	}
	public LocalDate getHorasData() {
		return horasData;
	}
	public void setHorasData(LocalDate horasData) {
		this.horasData = horasData;
	}
	public Long getUsuarioCod() {
		return usuarioCod;
	}
	public void setUsuarioCod(Long usuarioCod) {
		this.usuarioCod = usuarioCod;
	}
    
}
