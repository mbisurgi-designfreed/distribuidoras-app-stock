package com.designfreed.distribuidoras_app_stock.domain;

import java.io.Serializable;

public class EstadoEnvase implements Serializable {
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
