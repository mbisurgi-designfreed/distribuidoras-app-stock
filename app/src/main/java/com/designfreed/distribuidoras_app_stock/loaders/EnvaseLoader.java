package com.designfreed.distribuidoras_app_stock.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.designfreed.distribuidoras_app_stock.domain.Envase;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class EnvaseLoader extends AsyncTaskLoader<List<Envase>> {
    private String url;

    public EnvaseLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Envase> loadInBackground() {
        if (url == null) {
            return null;
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Envase[] envases = restTemplate.getForObject(url, Envase[].class);

            return Arrays.asList(envases);
        } catch (ResourceAccessException connectException) {
            return null;
        }
    }
}
