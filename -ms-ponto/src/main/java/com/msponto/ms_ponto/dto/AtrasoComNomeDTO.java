package com.msponto.ms_ponto.dto;

public class AtrasoComNomeDTO {
    private Long atrasoCod;
    private Float atrasoTempo;
    private HorasComNomeDTO horas;
    
	public Long getAtrasoCod() {
		return atrasoCod;
	}
	public void setAtrasoCod(Long atrasoCod) {
		this.atrasoCod = atrasoCod;
	}
	public Float getAtrasoTempo() {
		return atrasoTempo;
	}
	public void setAtrasoTempo(Float atrasoTempo) {
		this.atrasoTempo = atrasoTempo;
	}
	public HorasComNomeDTO getHoras() {
		return horas;
	}
	public void setHoras(HorasComNomeDTO horas) {
		this.horas = horas;
	}
    
    
}
