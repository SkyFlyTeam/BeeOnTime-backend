package com.msponto.ms_ponto.dto;

import java.time.LocalDate;

public class PeriodoDTO {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    } 

    public LocalDate getStartDateAsDate() {
        return LocalDate.parse(this.startDate);
    }

    public LocalDate getEndDateAsDate() {
        return LocalDate.parse(this.endDate);
    }
}
