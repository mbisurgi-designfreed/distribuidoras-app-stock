package com.designfreed.distribuidoras_app_stock;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.designfreed.distribuidoras_app_stock.domain.HojaRuta;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, List<HojaRuta>> {
        @Override
        protected List<HojaRuta> doInBackground(Void... params) {
            final String url = "http://192.168.0.9:8080/distribuidoras-backend/hojaRuta/findByFecha/1464663600000";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HojaRuta[] hojas = restTemplate.getForObject(url, HojaRuta[].class);

            return Arrays.asList(hojas);
        }

        @Override
        protected void onPostExecute(List<HojaRuta> hojaRutas) {
            super.onPostExecute(hojaRutas);

            for (HojaRuta hoja: hojaRutas) {
                System.out.println(hoja);
            }
        }
    }
}
