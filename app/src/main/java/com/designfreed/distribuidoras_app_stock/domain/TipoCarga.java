package com.designfreed.distribuidoras_app_stock.domain;

public class TipoCarga {
    private Long id;
    private String tipoCargaNombre;
    private String sigla;

    public TipoCarga() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoCargaNombre() {
        return tipoCargaNombre;
    }

    public void setTipoCargaNombre(String tipoCargaNombre) {
        this.tipoCargaNombre = tipoCargaNombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
