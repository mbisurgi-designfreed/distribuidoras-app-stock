package com.designfreed.distribuidoras_app_stock.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.designfreed.distribuidoras_app_stock.domain.HojaRuta;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class HojaRutaLoader extends AsyncTaskLoader<List<HojaRuta>> {
    private String url;

    public HojaRutaLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<HojaRuta> loadInBackground() {
        if (url == null) {
            return null;
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HojaRuta[] hojas = restTemplate.getForObject(url, HojaRuta[].class);

            return Arrays.asList(hojas);
        } catch (ResourceAccessException connectException) {
            return null;
        }
    }
}
