package com.designfreed.distribuidoras_app_stock.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.designfreed.distribuidoras_app_stock.R;
import com.designfreed.distribuidoras_app_stock.activities.CargaActivity;
import com.designfreed.distribuidoras_app_stock.domain.Carga;
import com.designfreed.distribuidoras_app_stock.domain.ItemCarga;
import com.designfreed.distribuidoras_app_stock.utils.Globales;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FragmentREN1 extends Fragment {
    private static final String SERVICE_URL = "http://bybgas.dyndns.org:8080/StockService/services/stockService/getCargaByHojaRuta?";
    private static final Long REN1 = 3L;
    private EditText lleno10;
    private EditText lleno12;
    private EditText lleno15;
    private EditText lleno15me;
    private EditText lleno30;
    private EditText lleno45;
    private EditText vacio10;
    private EditText vacio12;
    private EditText vacio15;
    private EditText vacio15me;
    private EditText vacio30;
    private EditText vacio45;
    private EditText averiado10;
    private EditText averiado12;
    private EditText averiado15;
    private EditText averiado15me;
    private EditText averiado30;
    private EditText averiado45;
    private EditText retiro10;
    private EditText retiro12;
    private EditText retiro15;
    private EditText retiro15me;
    private EditText retiro30;
    private EditText retiro45;
    private EditText entrega10;
    private EditText entrega12;
    private EditText entrega15;
    private EditText entrega15me;
    private EditText entrega30;
    private EditText entrega45;
    private EditText cambio10;
    private EditText cambio12;
    private EditText cambio15;
    private EditText cambio15me;
    private EditText cambio30;
    private EditText cambio45;
    private Button procesar;
    private TextView choferNombre;
    private String chofer;
    private Long hojaRutaId;
    Carga carga;

    public FragmentREN1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tipos_carga_ren1, container, false);

        hojaRutaId = getArguments().getLong("id");
        chofer = getArguments().getString("chofer");

        carga = (Carga) getArguments().getSerializable("cargaREN1");

        choferNombre = (TextView) rootView.findViewById(R.id.nombrechofer);
        choferNombre.setText(chofer);

        procesar = (Button) rootView.findViewById(R.id.procesar);

        lleno10 = (EditText) rootView.findViewById(R.id.lleno10);
        lleno10.setText(String.valueOf(0));

        lleno12 = (EditText) rootView.findViewById(R.id.lleno12);
        lleno12.setText(String.valueOf(0));

        lleno15 = (EditText) rootView.findViewById(R.id.lleno15);
        lleno15.setText(String.valueOf(0));

        lleno15me = (EditText) rootView.findViewById(R.id.lleno15me);
        lleno15me.setText(String.valueOf(0));

        lleno30 = (EditText) rootView.findViewById(R.id.lleno30);
        lleno30.setText(String.valueOf(0));

        lleno45 = (EditText) rootView.findViewById(R.id.lleno45);
        lleno45.setText(String.valueOf(0));

        vacio10 = (EditText) rootView.findViewById(R.id.vacio10);
        vacio10.setText(String.valueOf(0));

        vacio12 = (EditText) rootView.findViewById(R.id.vacio12);
        vacio12.setText(String.valueOf(0));

        vacio15 = (EditText) rootView.findViewById(R.id.vacio15);
        vacio15.setText(String.valueOf(0));

        vacio15me = (EditText) rootView.findViewById(R.id.vacio15me);
        vacio15me.setText(String.valueOf(0));

        vacio30 = (EditText) rootView.findViewById(R.id.vacio30);
        vacio30.setText(String.valueOf(0));

        vacio45 = (EditText) rootView.findViewById(R.id.vacio45);
        vacio45.setText(String.valueOf(0));

        averiado10 = (EditText) rootView.findViewById(R.id.averiado10);
        averiado10.setText(String.valueOf(0));

        averiado12 = (EditText) rootView.findViewById(R.id.averiado12);
        averiado12.setText(String.valueOf(0));

        averiado15 = (EditText) rootView.findViewById(R.id.averiado15);
        averiado15.setText(String.valueOf(0));

        averiado15me = (EditText) rootView.findViewById(R.id.averiado15me);
        averiado15me.setText(String.valueOf(0));

        averiado30 = (EditText) rootView.findViewById(R.id.averiado30);
        averiado30.setText(String.valueOf(0));

        averiado45 = (EditText) rootView.findViewById(R.id.averiado45);
        averiado45.setText(String.valueOf(0));

        retiro10 = (EditText) rootView.findViewById(R.id.retiro10);
        retiro10.setText(String.valueOf(0));

        retiro12 = (EditText) rootView.findViewById(R.id.retiro12);
        retiro12.setText(String.valueOf(0));

        retiro15 = (EditText) rootView.findViewById(R.id.retiro15);
        retiro15.setText(String.valueOf(0));

        retiro15me = (EditText) rootView.findViewById(R.id.retiro15me);
        retiro15me.setText(String.valueOf(0));

        retiro30 = (EditText) rootView.findViewById(R.id.retiro30);
        retiro30.setText(String.valueOf(0));

        retiro45 = (EditText) rootView.findViewById(R.id.retiro45);
        retiro45.setText(String.valueOf(0));

        entrega10 = (EditText) rootView.findViewById(R.id.entrega10);
        entrega10.setText(String.valueOf(0));

        entrega12 = (EditText) rootView.findViewById(R.id.entrega12);
        entrega12.setText(String.valueOf(0));

        entrega15 = (EditText) rootView.findViewById(R.id.entrega15);
        entrega15.setText(String.valueOf(0));

        entrega15me = (EditText) rootView.findViewById(R.id.entrega15me);
        entrega15me.setText(String.valueOf(0));

        entrega30 = (EditText) rootView.findViewById(R.id.entrega30);
        entrega30.setText(String.valueOf(0));

        entrega45 = (EditText) rootView.findViewById(R.id.entrega45);
        entrega45.setText(String.valueOf(0));

        cambio10 = (EditText) rootView.findViewById(R.id.cambio10);
        cambio10.setText(String.valueOf(0));

        cambio12 = (EditText) rootView.findViewById(R.id.cambio12);
        cambio12.setText(String.valueOf(0));

        cambio15 = (EditText) rootView.findViewById(R.id.cambio15);
        cambio15.setText(String.valueOf(0));

        cambio15me = (EditText) rootView.findViewById(R.id.cambio15me);
        cambio15me.setText(String.valueOf(0));

        cambio30 = (EditText) rootView.findViewById(R.id.cambio30);
        cambio30.setText(String.valueOf(0));

        cambio45 = (EditText) rootView.findViewById(R.id.cambio45);
        cambio45.setText(String.valueOf(0));

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostRendicion1AsyncTask().execute();
            }
        });

        cargarCarga(carga);

        return rootView;
    }

    private class PostRendicion1AsyncTask extends AsyncTask<Carga, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Carga... params) {
            String url = "http://192.168.0.9:8080/carga/saveOrUpdate";

            crearCarga(carga);

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Carga> response = restTemplate.postForEntity(url, carga, Carga.class);

                return  (response.getStatusCode() == HttpStatus.OK);
            } catch (ResourceAccessException connectException) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                Toast.makeText(getContext(), "Ingreso satisfactorio", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Ingreso fallido", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(getContext(), CargaActivity.class);
            intent.putExtra("id", hojaRutaId);
            intent.putExtra("chofer", chofer);
            startActivity(intent);
        }
    }

    private void crearCarga(Carga carga) {
        List<ItemCarga> items = new ArrayList<>();

        ItemCarga garrafa10 = new ItemCarga();
        garrafa10.setEnvase(Globales.getInstancia().getEnvases().get(0));
        garrafa10.setLleno(Integer.parseInt(lleno10.getText().toString()));
        garrafa10.setVacio(Integer.parseInt(vacio10.getText().toString()));
        garrafa10.setAveriado(Integer.parseInt(averiado10.getText().toString()));
        garrafa10.setRetiro(Integer.parseInt(retiro10.getText().toString()));
        garrafa10.setEntrega(Integer.parseInt(entrega10.getText().toString()));
        garrafa10.setCambio(Integer.parseInt(cambio10.getText().toString()));

        ItemCarga garrafa12 = new ItemCarga();
        garrafa12.setEnvase(Globales.getInstancia().getEnvases().get(1));
        garrafa12.setLleno(Integer.parseInt(lleno12.getText().toString()));
        garrafa12.setVacio(Integer.parseInt(vacio12.getText().toString()));
        garrafa12.setAveriado(Integer.parseInt(averiado12.getText().toString()));
        garrafa12.setRetiro(Integer.parseInt(retiro12.getText().toString()));
        garrafa12.setEntrega(Integer.parseInt(entrega12.getText().toString()));
        garrafa12.setCambio(Integer.parseInt(cambio12.getText().toString()));

        ItemCarga garrafa15 = new ItemCarga();
        garrafa15.setEnvase(Globales.getInstancia().getEnvases().get(2));
        garrafa15.setLleno(Integer.parseInt(lleno15.getText().toString()));
        garrafa15.setVacio(Integer.parseInt(vacio15.getText().toString()));
        garrafa15.setAveriado(Integer.parseInt(averiado15.getText().toString()));
        garrafa15.setRetiro(Integer.parseInt(retiro15.getText().toString()));
        garrafa15.setEntrega(Integer.parseInt(entrega15.getText().toString()));
        garrafa15.setCambio(Integer.parseInt(cambio15.getText().toString()));

        ItemCarga garrafa15me = new ItemCarga();
        garrafa15me.setEnvase(Globales.getInstancia().getEnvases().get(3));
        garrafa15me.setLleno(Integer.parseInt(lleno15me.getText().toString()));
        garrafa15me.setVacio(Integer.parseInt(vacio15me.getText().toString()));
        garrafa15me.setAveriado(Integer.parseInt(averiado15me.getText().toString()));
        garrafa15me.setRetiro(Integer.parseInt(retiro15me.getText().toString()));
        garrafa15me.setEntrega(Integer.parseInt(entrega15me.getText().toString()));
        garrafa15me.setCambio(Integer.parseInt(cambio15me.getText().toString()));

        ItemCarga garrafa30 = new ItemCarga();
        garrafa30.setEnvase(Globales.getInstancia().getEnvases().get(4));
        garrafa30.setLleno(Integer.parseInt(lleno30.getText().toString()));
        garrafa30.setVacio(Integer.parseInt(vacio30.getText().toString()));
        garrafa30.setAveriado(Integer.parseInt(averiado30.getText().toString()));
        garrafa30.setRetiro(Integer.parseInt(retiro30.getText().toString()));
        garrafa30.setEntrega(Integer.parseInt(entrega30.getText().toString()));
        garrafa30.setCambio(Integer.parseInt(cambio30.getText().toString()));

        ItemCarga garrafa45 = new ItemCarga();
        garrafa45.setEnvase(Globales.getInstancia().getEnvases().get(5));
        garrafa45.setLleno(Integer.parseInt(lleno45.getText().toString()));
        garrafa45.setVacio(Integer.parseInt(vacio45.getText().toString()));
        garrafa45.setAveriado(Integer.parseInt(averiado45.getText().toString()));
        garrafa45.setRetiro(Integer.parseInt(retiro45.getText().toString()));
        garrafa45.setEntrega(Integer.parseInt(entrega45.getText().toString()));
        garrafa45.setCambio(Integer.parseInt(cambio45.getText().toString()));

        items.add(garrafa10);
        items.add(garrafa12);
        items.add(garrafa15);
        items.add(garrafa15me);
        items.add(garrafa30);
        items.add(garrafa45);

        if (carga == null) {
            carga = new Carga();

            carga.setTipo(Globales.getInstancia().getTipos().get(2));
            carga.setItems(items);
        } else {
            for (ItemCarga item: carga.getItems()) {
                for (ItemCarga item1: items) {
                    if (item.getEnvase().getId().equals(item1.getEnvase().getId())) {
                        item.setLleno(item1.getLleno());
                        item.setVacio(item1.getVacio());
                        item.setAveriado(item1.getAveriado());
                        item.setRetiro(item1.getRetiro());
                        item.setEntrega(item1.getEntrega());
                        item.setCambio(item1.getCambio());
                    }
                }
            }
        }
    }

    private void cargarCarga(Carga carga) {
        if (carga != null) {
            for (ItemCarga item: carga.getItems()) {
                if (item.getEnvase().getId() == 1) {
                    lleno10.setText(String.valueOf(item.getLleno()));
                    vacio10.setText(String.valueOf(item.getVacio()));
                    averiado10.setText(String.valueOf(item.getAveriado()));
                    retiro10.setText(String.valueOf(item.getRetiro()));
                    entrega10.setText(String.valueOf(item.getEntrega()));
                    cambio10.setText(String.valueOf(item.getCambio()));
                }

                if (item.getEnvase().getId() == 2) {
                    lleno12.setText(String.valueOf(item.getLleno()));
                    vacio12.setText(String.valueOf(item.getVacio()));
                    averiado12.setText(String.valueOf(item.getAveriado()));
                    retiro12.setText(String.valueOf(item.getRetiro()));
                    entrega12.setText(String.valueOf(item.getEntrega()));
                    cambio12.setText(String.valueOf(item.getCambio()));
                }

                if (item.getEnvase().getId() == 3) {
                    lleno15.setText(String.valueOf(item.getLleno()));
                    vacio15.setText(String.valueOf(item.getVacio()));
                    averiado15.setText(String.valueOf(item.getAveriado()));
                    retiro15.setText(String.valueOf(item.getRetiro()));
                    entrega15.setText(String.valueOf(item.getEntrega()));
                    cambio15.setText(String.valueOf(item.getCambio()));
                }

                if (item.getEnvase().getId() == 4) {
                    lleno15me.setText(String.valueOf(item.getLleno()));
                    vacio15me.setText(String.valueOf(item.getVacio()));
                    averiado15me.setText(String.valueOf(item.getAveriado()));
                    retiro15me.setText(String.valueOf(item.getRetiro()));
                    entrega15me.setText(String.valueOf(item.getEntrega()));
                    cambio15me.setText(String.valueOf(item.getCambio()));
                }

                if (item.getEnvase().getId() == 5) {
                    lleno30.setText(String.valueOf(item.getLleno()));
                    vacio30.setText(String.valueOf(item.getVacio()));
                    averiado30.setText(String.valueOf(item.getAveriado()));
                    retiro30.setText(String.valueOf(item.getRetiro()));
                    entrega30.setText(String.valueOf(item.getEntrega()));
                    cambio30.setText(String.valueOf(item.getCambio()));
                }

                if (item.getEnvase().getId() == 6) {
                    lleno45.setText(String.valueOf(item.getLleno()));
                    vacio45.setText(String.valueOf(item.getVacio()));
                    averiado45.setText(String.valueOf(item.getAveriado()));
                    retiro45.setText(String.valueOf(item.getRetiro()));
                    entrega45.setText(String.valueOf(item.getEntrega()));
                    cambio45.setText(String.valueOf(item.getCambio()));
                }
            }
        }
    }

    private void reset() {
        lleno10.setText(String.valueOf(0));
        vacio10.setText(String.valueOf(0));
        averiado10.setText(String.valueOf(0));
        retiro10.setText(String.valueOf(0));
        entrega10.setText(String.valueOf(0));
        cambio10.setText(String.valueOf(0));

        lleno12.setText(String.valueOf(0));
        vacio12.setText(String.valueOf(0));
        averiado12.setText(String.valueOf(0));
        retiro12.setText(String.valueOf(0));
        entrega12.setText(String.valueOf(0));
        cambio12.setText(String.valueOf(0));

        lleno15.setText(String.valueOf(0));
        vacio15.setText(String.valueOf(0));
        averiado15.setText(String.valueOf(0));
        retiro15.setText(String.valueOf(0));
        entrega15.setText(String.valueOf(0));
        cambio15.setText(String.valueOf(0));

        lleno15me.setText(String.valueOf(0));
        vacio15me.setText(String.valueOf(0));
        averiado15me.setText(String.valueOf(0));
        retiro15me.setText(String.valueOf(0));
        entrega15me.setText(String.valueOf(0));
        cambio15me.setText(String.valueOf(0));

        lleno30.setText(String.valueOf(0));
        vacio30.setText(String.valueOf(0));
        averiado30.setText(String.valueOf(0));
        retiro30.setText(String.valueOf(0));
        entrega30.setText(String.valueOf(0));
        cambio30.setText(String.valueOf(0));

        lleno45.setText(String.valueOf(0));
        vacio45.setText(String.valueOf(0));
        averiado45.setText(String.valueOf(0));
        retiro45.setText(String.valueOf(0));
        entrega45.setText(String.valueOf(0));
        cambio45.setText(String.valueOf(0));
    }
}
