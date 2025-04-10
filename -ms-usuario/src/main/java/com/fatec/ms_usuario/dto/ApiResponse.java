package com.fatec.ms_usuario.dto;

public class ApiResponse {
    private boolean exists;

    // Construtor
    public ApiResponse(boolean exists) {
        this.exists = exists;
    }

    // Getter
    public boolean isExists() {
        return exists;
    }

    // Setter
    public void setExists(boolean exists) {
        this.exists = exists;
    }
}

