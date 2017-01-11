package com.designfreed.distribuidoras_app_stock.domain;

public class EstadoMovimiento {
    private Long id;
    private String estadoMovimientoNombre;

    public EstadoMovimiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoMovimientoNombre() {
        return estadoMovimientoNombre;
    }

    public void setEstadoMovimientoNombre(String estadoMovimientoNombre) {
        this.estadoMovimientoNombre = estadoMovimientoNombre;
    }
}
