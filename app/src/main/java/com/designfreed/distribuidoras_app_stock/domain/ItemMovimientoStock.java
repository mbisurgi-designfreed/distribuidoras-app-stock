package com.designfreed.distribuidoras_app_stock.domain;

public class ItemMovimientoStock {
    private Long id;
    private Envase envase;
    private EstadoEnvase estadoEnvase;
    private Integer cantidad;
    private Float costo;
    private Boolean comodatoGenerado;

    public ItemMovimientoStock() {
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

    public EstadoEnvase getEstadoEnvase() {
        return estadoEnvase;
    }

    public void setEstadoEnvase(EstadoEnvase estadoEnvase) {
        this.estadoEnvase = estadoEnvase;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Boolean getComodatoGenerado() {
        return comodatoGenerado;
    }

    public void setComodatoGenerado(Boolean comodatoGenerado) {
        this.comodatoGenerado = comodatoGenerado;
    }
}
