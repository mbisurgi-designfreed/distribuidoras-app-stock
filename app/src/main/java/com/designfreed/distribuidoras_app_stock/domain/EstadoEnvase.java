package com.designfreed.distribuidoras_app_stock.domain;

public class EstadoEnvase {
    private Long id;
    private String estadoEnvaseNombre;

    public EstadoEnvase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoEnvaseNombre() {
        return estadoEnvaseNombre;
    }

    public void setEstadoEnvaseNombre(String estadoEnvaseNombre) {
        this.estadoEnvaseNombre = estadoEnvaseNombre;
    }
}
