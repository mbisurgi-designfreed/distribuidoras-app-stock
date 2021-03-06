package com.designfreed.distribuidoras_app_stock.domain;

import java.io.Serializable;

public class ItemMovimiento implements Serializable {
    private Long id;
    private Envase envase;
    private Integer cantidad;

    public ItemMovimiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
