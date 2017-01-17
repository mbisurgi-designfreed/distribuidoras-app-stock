package com.designfreed.distribuidoras_app_stock.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.designfreed.distribuidoras_app_stock.domain.Carga;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CargaLoader extends AsyncTaskLoader<List<Carga>> {
    private String url;

    public CargaLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Carga> loadInBackground() {
        if (url == null) {
            return null;
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Carga[] cargas = restTemplate.getForObject(url, Carga[].class);

            return Arrays.asList(cargas);
        } catch (ResourceAccessException connectException) {
            return null;
        }
    }
}
