package com.designfreed.distribuidoras_app_stock.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoStock implements Serializable {
    private Long id;
    private Date fecha;
    private HojaRuta hojaRuta;
    private String tipoMovimiento;
    private String modulo;
    private String nroComprobante;
    private List<ItemMovimientoStock> items = new ArrayList<>();

    public MovimientoStock() {
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

    public HojaRuta getHojaRuta() {
        return hojaRuta;
    }

    public void setHojaRuta(HojaRuta hojaRuta) {
        this.hojaRuta = hojaRuta;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public List<ItemMovimientoStock> getItems() {
        return items;
    }

    public void setItems(List<ItemMovimientoStock> items) {
        this.items = items;
    }
}
