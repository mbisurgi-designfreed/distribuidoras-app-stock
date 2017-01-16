package com.designfreed.distribuidoras_app_stock.utils;

import android.os.AsyncTask;

import com.designfreed.distribuidoras_app_stock.domain.Envase;
import com.designfreed.distribuidoras_app_stock.domain.EstadoEnvase;
import com.designfreed.distribuidoras_app_stock.domain.TipoCarga;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class Globales {
    private static Globales instancia = null;
    private List<Envase> envases;
    private List<TipoCarga> tipos;

    private Globales() {
        new LoadEnvasesTask();
        new LoadTiposCargaTask();
    }

    public static Globales getInstancia() {
        if (instancia == null) {
            instancia = new Globales();
        }

        return instancia;
    }

    public List<Envase> getEnvases() {
        return envases;
    }

    public List<TipoCarga> getTipos() {
        return tipos;
    }

    private class LoadEnvasesTask extends AsyncTask<Void, Void, List<Envase>> {
        @Override
        protected List<Envase> doInBackground(Void... params) {
            String url = "http://192.168.0.9:8080/envase/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Envase[] envases = restTemplate.getForObject(url, Envase[].class);

                return Arrays.asList(envases);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Envase> result) {
            super.onPostExecute(result);

            envases = result;
        }
    }

    private class LoadTiposCargaTask extends AsyncTask<Void, Void, List<TipoCarga>> {
        @Override
        protected List<TipoCarga> doInBackground(Void... params) {
            String url = "http://192.168.0.9:8080/tipoCarga/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                TipoCarga[] tipos = restTemplate.getForObject(url, TipoCarga[].class);

                return Arrays.asList(tipos);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<TipoCarga> result) {
            super.onPostExecute(result);

            tipos = result;
        }
    }
}
