package com.designfreed.distribuidoras_app_stock.loaders;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.designfreed.distribuidoras_app_stock.domain.Movimiento;
import com.designfreed.distribuidoras_app_stock.domain.MovimientoStock;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class MovimientoLoader extends AsyncTaskLoader<List<Movimiento>> {
    private String url;

    public MovimientoLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Movimiento> loadInBackground() {
        if (url == null) {
            return null;
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Movimiento[] movimientos = restTemplate.getForObject(url, Movimiento[].class);

            return Arrays.asList(movimientos);
        } catch (ResourceAccessException connectException) {
            return null;
        }
    }
}
