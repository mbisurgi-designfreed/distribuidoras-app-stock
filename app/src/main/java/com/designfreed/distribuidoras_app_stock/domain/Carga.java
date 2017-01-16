package com.designfreed.distribuidoras_app_stock.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Carga implements Serializable {
    private Long id;
    private Date fecha;
    private TipoCarga tipo;
    private HojaRuta hojaRuta;
    private List<ItemCarga> items = new ArrayList<>();

    public Carga() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoCarga getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarga tipo) {
        this.tipo = tipo;
    }

    public HojaRuta getHojaRuta() {
        return hojaRuta;
    }

    public void setHojaRuta(HojaRuta hojaRuta) {
        this.hojaRuta = hojaRuta;
    }

    public List<ItemCarga> getItems() {
        return items;
    }

    public void setItems(List<ItemCarga> items) {
        this.items = items;
    }
}
