package com.designfreed.distribuidoras_app_stock.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public Long dateToLong(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String newFechaString = formatter.format(fecha);

        try {
            fecha = formatter.parse(newFechaString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha.getTime();
    }

    public Date longToDate(Long fecha) {
        return new Date(fecha);
    }
}
