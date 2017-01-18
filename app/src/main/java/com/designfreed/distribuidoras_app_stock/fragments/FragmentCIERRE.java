package com.designfreed.distribuidoras_app_stock.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.designfreed.distribuidoras_app_stock.R;
import com.designfreed.distribuidoras_app_stock.activities.MainActivity;
import com.designfreed.distribuidoras_app_stock.domain.Carga;
import com.designfreed.distribuidoras_app_stock.domain.HojaRuta;
import com.designfreed.distribuidoras_app_stock.domain.ItemCarga;
import com.designfreed.distribuidoras_app_stock.domain.ItemMovimiento;
import com.designfreed.distribuidoras_app_stock.domain.Movimiento;
import com.designfreed.distribuidoras_app_stock.loaders.MovimientoLoader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FragmentCIERRE extends Fragment implements LoaderManager.LoaderCallbacks<List<Movimiento>> {
    private static final String SERVICE_URL = "http://bybgas.dyndns.org:8080/StockService/services/stockService/getMovimientoByHojaRuta?";
    private TextView inicLleno10;
    private TextView inicLleno12;
    private TextView inicLleno15;
    private TextView inicLleno15me;
    private TextView inicLleno30;
    private TextView inicLleno45;
    private TextView repoLleno10;
    private TextView repoLleno12;
    private TextView repoLleno15;
    private TextView repoLleno15me;
    private TextView repoLleno30;
    private TextView repoLleno45;
    private TextView repoVacio10;
    private TextView repoVacio12;
    private TextView repoVacio15;
    private TextView repoVacio15me;
    private TextView repoVacio30;
    private TextView repoVacio45;
    private TextView rendLleno10;
    private TextView rendLleno12;
    private TextView rendLleno15;
    private TextView rendLleno15me;
    private TextView rendLleno30;
    private TextView rendLleno45;
    private TextView rendVacio10;
    private TextView rendVacio12;
    private TextView rendVacio15;
    private TextView rendVacio15me;
    private TextView rendVacio30;
    private TextView rendVacio45;
    private TextView rendAveriado10;
    private TextView rendAveriado12;
    private TextView rendAveriado15;
    private TextView rendAveriado15me;
    private TextView rendAveriado30;
    private TextView rendAveriado45;
    private TextView rendRetiro10;
    private TextView rendRetiro12;
    private TextView rendRetiro15;
    private TextView rendRetiro15me;
    private TextView rendRetiro30;
    private TextView rendRetiro45;
    private TextView rendEntrega10;
    private TextView rendEntrega12;
    private TextView rendEntrega15;
    private TextView rendEntrega15me;
    private TextView rendEntrega30;
    private TextView rendEntrega45;
    private TextView rendCambio10;
    private TextView rendCambio12;
    private TextView rendCambio15;
    private TextView rendCambio15me;
    private TextView rendCambio30;
    private TextView rendCambio45;
    private TextView ventaProducto10;
    private TextView ventaProducto12;
    private TextView ventaProducto15;
    private TextView ventaProducto15me;
    private TextView ventaProducto30;
    private TextView ventaProducto45;
    private TextView ventaEnvase10;
    private TextView ventaEnvase12;
    private TextView ventaEnvase15;
    private TextView ventaEnvase15me;
    private TextView ventaEnvase30;
    private TextView ventaEnvase45;
    private TextView compraEnvase10;
    private TextView compraEnvase12;
    private TextView compraEnvase15;
    private TextView compraEnvase15me;
    private TextView compraEnvase30;
    private TextView compraEnvase45;
    private TextView control10;
    private TextView control12;
    private TextView control15;
    private TextView control15me;
    private TextView control30;
    private TextView control45;
    private TextView choferNombre;
    private Button procesar;
    private String chofer;
    private HojaRuta hojaRuta;
    private List<Carga> cargas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cierre_stock, container, false);

        hojaRuta = (HojaRuta) getArguments().getSerializable("hoja");
        chofer = getArguments().getString("chofer");

        choferNombre = (TextView) rootView.findViewById(R.id.nombrechofer);
        choferNombre.setText(chofer);

        procesar = (Button) rootView.findViewById(R.id.procesar);

        cargas = (List<Carga>) getArguments().getSerializable("cargas");

        inicLleno10 = (TextView) rootView.findViewById(R.id.inicLleno10);
        inicLleno12 = (TextView) rootView.findViewById(R.id.inicLleno12);
        inicLleno15 = (TextView) rootView.findViewById(R.id.inicLleno15);
        inicLleno15me = (TextView) rootView.findViewById(R.id.inicLleno15me);
        inicLleno30 = (TextView) rootView.findViewById(R.id.inicLleno30);
        inicLleno45 = (TextView) rootView.findViewById(R.id.inicLleno45);

        repoLleno10 = (TextView) rootView.findViewById(R.id.repoLleno10);
        repoLleno12 = (TextView) rootView.findViewById(R.id.repoLleno12);
        repoLleno15 = (TextView) rootView.findViewById(R.id.repoLleno15);
        repoLleno15me = (TextView) rootView.findViewById(R.id.repoLleno15me);
        repoLleno30 = (TextView) rootView.findViewById(R.id.repoLleno30);
        repoLleno45 = (TextView) rootView.findViewById(R.id.repoLleno45);

        repoVacio10 = (TextView) rootView.findViewById(R.id.repoVacio10);
        repoVacio12 = (TextView) rootView.findViewById(R.id.repoVacio12);
        repoVacio15 = (TextView) rootView.findViewById(R.id.repoVacio15);
        repoVacio15me = (TextView) rootView.findViewById(R.id.repoVacio15me);
        repoVacio30 = (TextView) rootView.findViewById(R.id.repoVacio30);
        repoVacio45 = (TextView) rootView.findViewById(R.id.repoVacio45);

        rendLleno10 = (TextView) rootView.findViewById(R.id.rendLleno10);
        rendLleno12 = (TextView) rootView.findViewById(R.id.rendLleno12);
        rendLleno15 = (TextView) rootView.findViewById(R.id.rendLleno15);
        rendLleno15me = (TextView) rootView.findViewById(R.id.rendLleno15me);
        rendLleno30 = (TextView) rootView.findViewById(R.id.rendLleno30);
        rendLleno45 = (TextView) rootView.findViewById(R.id.rendLleno45);

        rendVacio10 = (TextView) rootView.findViewById(R.id.rendVacio10);
        rendVacio12 = (TextView) rootView.findViewById(R.id.rendVacio12);
        rendVacio15 = (TextView) rootView.findViewById(R.id.rendVacio15);
        rendVacio15me = (TextView) rootView.findViewById(R.id.rendVacio15me);
        rendVacio30 = (TextView) rootView.findViewById(R.id.rendVacio30);
        rendVacio45 = (TextView) rootView.findViewById(R.id.rendVacio45);

        rendAveriado10 = (TextView) rootView.findViewById(R.id.rendAveriado10);
        rendAveriado12 = (TextView) rootView.findViewById(R.id.rendAveriado12);
        rendAveriado15 = (TextView) rootView.findViewById(R.id.rendAveriado15);
        rendAveriado15me = (TextView) rootView.findViewById(R.id.rendAveriado15me);
        rendAveriado30 = (TextView) rootView.findViewById(R.id.rendAveriado30);
        rendAveriado45 = (TextView) rootView.findViewById(R.id.rendAveriado45);

        rendRetiro10 = (TextView) rootView.findViewById(R.id.rendRetiro10);
        rendRetiro12 = (TextView) rootView.findViewById(R.id.rendRetiro12);
        rendRetiro15 = (TextView) rootView.findViewById(R.id.rendRetiro15);
        rendRetiro15me = (TextView) rootView.findViewById(R.id.rendRetiro15me);
        rendRetiro30 = (TextView) rootView.findViewById(R.id.rendRetiro30);
        rendRetiro45 = (TextView) rootView.findViewById(R.id.rendRetiro45);

        rendEntrega10 = (TextView) rootView.findViewById(R.id.rendEntrega10);
        rendEntrega12 = (TextView) rootView.findViewById(R.id.rendEntrega12);
        rendEntrega15 = (TextView) rootView.findViewById(R.id.rendEntrega15);
        rendEntrega15me = (TextView) rootView.findViewById(R.id.rendEntrega15me);
        rendEntrega30 = (TextView) rootView.findViewById(R.id.rendEntrega30);
        rendEntrega45 = (TextView) rootView.findViewById(R.id.rendEntrega45);

        rendCambio10 = (TextView) rootView.findViewById(R.id.rendCambio10);
        rendCambio12 = (TextView) rootView.findViewById(R.id.rendCambio12);
        rendCambio15 = (TextView) rootView.findViewById(R.id.rendCambio15);
        rendCambio15me = (TextView) rootView.findViewById(R.id.rendCambio15me);
        rendCambio30 = (TextView) rootView.findViewById(R.id.rendCambio30);
        rendCambio45 = (TextView) rootView.findViewById(R.id.rendCambio45);

        ventaProducto10 = (TextView) rootView.findViewById(R.id.ventaProducto10);
        ventaProducto12 = (TextView) rootView.findViewById(R.id.ventaProducto12);
        ventaProducto15 = (TextView) rootView.findViewById(R.id.ventaProducto15);
        ventaProducto15me = (TextView) rootView.findViewById(R.id.ventaProducto15me);
        ventaProducto30 = (TextView) rootView.findViewById(R.id.ventaProducto30);
        ventaProducto45 = (TextView) rootView.findViewById(R.id.ventaProducto45);

        ventaEnvase10 = (TextView) rootView.findViewById(R.id.ventaEnvase10);
        ventaEnvase12 = (TextView) rootView.findViewById(R.id.ventaEnvase12);
        ventaEnvase15 = (TextView) rootView.findViewById(R.id.ventaEnvase15);
        ventaEnvase15me = (TextView) rootView.findViewById(R.id.ventaEnvase15me);
        ventaEnvase30 = (TextView) rootView.findViewById(R.id.ventaEnvase30);
        ventaEnvase45 = (TextView) rootView.findViewById(R.id.ventaEnvase45);

        compraEnvase10 = (TextView) rootView.findViewById(R.id.compraEnvase10);
        compraEnvase12 = (TextView) rootView.findViewById(R.id.compraEnvase12);
        compraEnvase15 = (TextView) rootView.findViewById(R.id.compraEnvase15);
        compraEnvase15me = (TextView) rootView.findViewById(R.id.compraEnvase15me);
        compraEnvase30 = (TextView) rootView.findViewById(R.id.compraEnvase30);
        compraEnvase45 = (TextView) rootView.findViewById(R.id.compraEnvase45);

        control10 = (TextView) rootView.findViewById(R.id.control10);
        control12 = (TextView) rootView.findViewById(R.id.control12);
        control15 = (TextView) rootView.findViewById(R.id.control15);
        control15me = (TextView) rootView.findViewById(R.id.control15me);
        control30 = (TextView) rootView.findViewById(R.id.control30);
        control45 = (TextView) rootView.findViewById(R.id.control45);

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrar();
            }
        });

        cargarCargaInicial(cargas);
        cargarReposicion(cargas);
        cargarRendiciones(cargas);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(1, null, this).forceLoad();
        } else {

        }

        return rootView;
    }

    private class PostCierreAsyncTask extends AsyncTask<HojaRuta, Void, Boolean> {
        @Override
        protected Boolean doInBackground(HojaRuta... params) {
            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/hojaRuta/update";

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<HojaRuta> response = restTemplate.postForEntity(url, hojaRuta, HojaRuta.class);

                return  (response.getStatusCode() == HttpStatus.OK);
            } catch (ResourceAccessException connectException) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                Toast.makeText(getContext(), "Cierre satisfactorio", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Cierre fallido", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void cargarCargaInicial(List<Carga> cargas) {
        Carga cargaInicial = null;

        for (Carga carga: cargas) {
            if (carga.getTipo().getId().equals(1L)) {
                cargaInicial = carga;
                break;
            }
        }

        Integer lleno10 = 0;
        Integer lleno12 = 0;
        Integer lleno15 = 0;
        Integer lleno15me = 0;
        Integer lleno30 = 0;
        Integer lleno45 = 0;

        if (cargaInicial != null) {
            for (ItemCarga item: cargaInicial.getItems()) {
                if (item.getEnvase().getId().equals(1L)) {
                    lleno10 = item.getLleno();
                }

                if (item.getEnvase().getId().equals(2L)) {
                    lleno12 = item.getLleno();
                }

                if (item.getEnvase().getId().equals(3L)) {
                    lleno15 = item.getLleno();
                }

                if (item.getEnvase().getId().equals(4L)) {
                    lleno15me = item.getLleno();
                }

                if (item.getEnvase().getId().equals(5L)) {
                    lleno30 = item.getLleno();
                }

                if (item.getEnvase().getId().equals(6L)) {
                    lleno45 = item.getLleno();
                }
            }
        }

        inicLleno10.setText(String.valueOf(lleno10));
        inicLleno12.setText(String.valueOf(lleno12));
        inicLleno15.setText(String.valueOf(lleno15));
        inicLleno15me.setText(String.valueOf(lleno15me));
        inicLleno30.setText(String.valueOf(lleno30));
        inicLleno45.setText(String.valueOf(lleno45));
    }

    private void cargarReposicion(List<Carga> cargas) {
        Carga cargaReposicion = null;

        for (Carga carga: cargas) {
            if (carga.getTipo().getId().equals(2L)) {
                cargaReposicion = carga;
                break;
            }
        }

        Integer lleno10 = 0;
        Integer lleno12 = 0;
        Integer lleno15 = 0;
        Integer lleno15me = 0;
        Integer lleno30 = 0;
        Integer lleno45 = 0;

        Integer vacio10 = 0;
        Integer vacio12 = 0;
        Integer vacio15 = 0;
        Integer vacio15me = 0;
        Integer vacio30 = 0;
        Integer vacio45 = 0;

        if (cargaReposicion != null) {
            for (ItemCarga item: cargaReposicion.getItems()) {
                if (item.getEnvase().getId().equals(1L)) {
                    lleno10 = item.getLleno();
                    vacio10 = item.getVacio();
                }

                if (item.getEnvase().getId().equals(2L)) {
                    lleno12 = item.getLleno();
                    vacio12 = item.getVacio();
                }

                if (item.getEnvase().getId().equals(3L)) {
                    lleno15 = item.getLleno();
                    vacio15 = item.getVacio();
                }

                if (item.getEnvase().getId().equals(4L)) {
                    lleno15me = item.getLleno();
                    vacio15me = item.getVacio();
                }

                if (item.getEnvase().getId().equals(5L)) {
                    lleno30 = item.getLleno();
                    vacio30 = item.getVacio();
                }

                if (item.getEnvase().getId().equals(6L)) {
                    lleno45 = item.getLleno();
                    vacio45 = item.getVacio();
                }
            }
        }

        repoLleno10.setText(String.valueOf(lleno10));
        repoLleno12.setText(String.valueOf(lleno12));
        repoLleno15.setText(String.valueOf(lleno15));
        repoLleno15me.setText(String.valueOf(lleno15me));
        repoLleno30.setText(String.valueOf(lleno30));
        repoLleno45.setText(String.valueOf(lleno45));

        repoVacio10.setText(String.valueOf(vacio10));
        repoVacio12.setText(String.valueOf(vacio12));
        repoVacio15.setText(String.valueOf(vacio15));
        repoVacio15me.setText(String.valueOf(vacio15me));
        repoVacio30.setText(String.valueOf(vacio30));
        repoVacio45.setText(String.valueOf(vacio45));
    }

    private void cargarRendiciones(List<Carga> cargas) {
        Carga cargaRendicion1 = null;
        Carga cargaRendicion2 = null;

        for (Carga carga: cargas) {
            if (carga.getTipo().getId().equals(3L)) {
                cargaRendicion1 = carga;
                break;
            }
        }

        for (Carga carga: cargas) {
            if (carga.getTipo().getId().equals(4L)) {
                cargaRendicion2 = carga;
                break;
            }
        }

        Integer lleno10 = 0;
        Integer lleno12 = 0;
        Integer lleno15 = 0;
        Integer lleno15me = 0;
        Integer lleno30 = 0;
        Integer lleno45 = 0;

        Integer vacio10 = 0;
        Integer vacio12 = 0;
        Integer vacio15 = 0;
        Integer vacio15me = 0;
        Integer vacio30 = 0;
        Integer vacio45 = 0;

        Integer averiado10 = 0;
        Integer averiado12 = 0;
        Integer averiado15 = 0;
        Integer averiado15me = 0;
        Integer averiado30 = 0;
        Integer averiado45 = 0;

        Integer retiro10 = 0;
        Integer retiro12 = 0;
        Integer retiro15 = 0;
        Integer retiro15me = 0;
        Integer retiro30 = 0;
        Integer retiro45 = 0;

        Integer entrega10 = 0;
        Integer entrega12 = 0;
        Integer entrega15 = 0;
        Integer entrega15me = 0;
        Integer entrega30 = 0;
        Integer entrega45 = 0;

        Integer cambio10 = 0;
        Integer cambio12 = 0;
        Integer cambio15 = 0;
        Integer cambio15me = 0;
        Integer cambio30 = 0;
        Integer cambio45 = 0;

        if (cargaRendicion1 != null) {
            for (ItemCarga item: cargaRendicion1.getItems()) {
                if (item.getEnvase().getId().equals(1L)) {
                    lleno10 = lleno10 + item.getLleno();
                    vacio10 = vacio10 + item.getVacio();
                    averiado10 = averiado10 + item.getAveriado();
                    retiro10 = retiro10 + item.getRetiro();
                    entrega10 = entrega10 + item.getEntrega();
                    cambio10 = cambio10 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(2L)) {
                    lleno12 = lleno12 + item.getLleno();
                    vacio12 = vacio12 + item.getVacio();
                    averiado12 = averiado12 + item.getAveriado();
                    retiro12 = retiro12 + item.getRetiro();
                    entrega12 = entrega12 + item.getEntrega();
                    cambio12 = cambio12 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(3L)) {
                    lleno15 = lleno15 + item.getLleno();
                    vacio15 = vacio15 + item.getVacio();
                    averiado15 = averiado15 + item.getAveriado();
                    retiro15 = retiro15 + item.getRetiro();
                    entrega15 = entrega15 + item.getEntrega();
                    cambio15 = cambio15 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(4L)) {
                    lleno15me = lleno15me + item.getLleno();
                    vacio15me = vacio15me + item.getVacio();
                    averiado15me = averiado15me + item.getAveriado();
                    retiro15me = retiro15me + item.getRetiro();
                    entrega15me = entrega15me + item.getEntrega();
                    cambio15me = cambio15me + item.getCambio();
                }

                if (item.getEnvase().getId().equals(5L)) {
                    lleno30 = lleno30 + item.getLleno();
                    vacio30 = vacio30 + item.getVacio();
                    averiado30 = averiado30 + item.getAveriado();
                    retiro30 = retiro30 + item.getRetiro();
                    entrega30 = entrega30 + item.getEntrega();
                    cambio30 = cambio30 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(6L)) {
                    lleno45 = lleno45 + item.getLleno();
                    vacio45 = vacio45 + item.getVacio();
                    averiado45 = averiado45 + item.getAveriado();
                    retiro45 = retiro45 + item.getRetiro();
                    entrega45 = entrega45 + item.getEntrega();
                    cambio45 = cambio45 + item.getCambio();
                }
            }
        }

        if (cargaRendicion2 != null) {
            for (ItemCarga item: cargaRendicion2.getItems()) {
                if (item.getEnvase().getId().equals(1L)) {
                    lleno10 = lleno10 + item.getLleno();
                    vacio10 = vacio10 + item.getVacio();
                    averiado10 = averiado10 + item.getAveriado();
                    retiro10 = retiro10 + item.getRetiro();
                    entrega10 = entrega10 + item.getEntrega();
                    cambio10 = cambio10 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(2L)) {
                    lleno12 = lleno12 + item.getLleno();
                    vacio12 = vacio12 + item.getVacio();
                    averiado12 = averiado12 + item.getAveriado();
                    retiro12 = retiro12 + item.getRetiro();
                    entrega12 = entrega12 + item.getEntrega();
                    cambio12 = cambio12 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(3L)) {
                    lleno15 = lleno15 + item.getLleno();
                    vacio15 = vacio15 + item.getVacio();
                    averiado15 = averiado15 + item.getAveriado();
                    retiro15 = retiro15 + item.getRetiro();
                    entrega15 = entrega15 + item.getEntrega();
                    cambio15 = cambio15 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(4L)) {
                    lleno15me = lleno15me + item.getLleno();
                    vacio15me = vacio15me + item.getVacio();
                    averiado15me = averiado15me + item.getAveriado();
                    retiro15me = retiro15me + item.getRetiro();
                    entrega15me = entrega15me + item.getEntrega();
                    cambio15me = cambio15me + item.getCambio();
                }

                if (item.getEnvase().getId().equals(5L)) {
                    lleno30 = lleno30 + item.getLleno();
                    vacio30 = vacio30 + item.getVacio();
                    averiado30 = averiado30 + item.getAveriado();
                    retiro30 = retiro30 + item.getRetiro();
                    entrega30 = entrega30 + item.getEntrega();
                    cambio30 = cambio30 + item.getCambio();
                }

                if (item.getEnvase().getId().equals(6L)) {
                    lleno45 = lleno45 + item.getLleno();
                    vacio45 = vacio45 + item.getVacio();
                    averiado45 = averiado45 + item.getAveriado();
                    retiro45 = retiro45 + item.getRetiro();
                    entrega45 = entrega45 + item.getEntrega();
                    cambio45 = cambio45 + item.getCambio();
                }
            }
        }

        rendLleno10.setText(String.valueOf(lleno10));
        rendLleno12.setText(String.valueOf(lleno12));
        rendLleno15.setText(String.valueOf(lleno15));
        rendLleno15me.setText(String.valueOf(lleno15me));
        rendLleno30.setText(String.valueOf(lleno30));
        rendLleno45.setText(String.valueOf(lleno45));

        rendVacio10.setText(String.valueOf(vacio10));
        rendVacio12.setText(String.valueOf(vacio12));
        rendVacio15.setText(String.valueOf(vacio15));
        rendVacio15me.setText(String.valueOf(vacio15me));
        rendVacio30.setText(String.valueOf(vacio30));
        rendVacio45.setText(String.valueOf(vacio45));

        rendAveriado10.setText(String.valueOf(averiado10));
        rendAveriado12.setText(String.valueOf(averiado12));
        rendAveriado15.setText(String.valueOf(averiado15));
        rendAveriado15me.setText(String.valueOf(averiado15me));
        rendAveriado30.setText(String.valueOf(averiado30));
        rendAveriado45.setText(String.valueOf(averiado45));

        rendRetiro10.setText(String.valueOf(retiro10));
        rendRetiro12.setText(String.valueOf(retiro12));
        rendRetiro15.setText(String.valueOf(retiro15));
        rendRetiro15me.setText(String.valueOf(retiro15me));
        rendRetiro30.setText(String.valueOf(retiro30));
        rendRetiro45.setText(String.valueOf(retiro45));

        rendEntrega10.setText(String.valueOf(entrega10));
        rendEntrega12.setText(String.valueOf(entrega12));
        rendEntrega15.setText(String.valueOf(entrega15));
        rendEntrega15me.setText(String.valueOf(entrega15me));
        rendEntrega30.setText(String.valueOf(entrega30));
        rendEntrega45.setText(String.valueOf(entrega45));

        rendCambio10.setText(String.valueOf(cambio10));
        rendCambio12.setText(String.valueOf(cambio12));
        rendCambio15.setText(String.valueOf(cambio15));
        rendCambio15me.setText(String.valueOf(cambio15me));
        rendCambio30.setText(String.valueOf(cambio30));
        rendCambio45.setText(String.valueOf(cambio45));
    }

    private void cargarVentasProducto(List<Movimiento> movimientos) {
        Integer venta10 = 0;
        Integer venta12 = 0;
        Integer venta15 = 0;
        Integer venta15me = 0;
        Integer venta30 = 0;
        Integer venta45 = 0;

        for (Movimiento mov: movimientos) {
            for (ItemMovimiento item: mov.getItems()) {
                if (item.getEnvase().getId().equals(1L)) {
                    venta10 = venta10 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(2L)) {
                    venta12 = venta12 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(3L)) {
                    venta15 = venta15 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(4L)) {
                    venta15me = venta15me + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(5L)) {
                    venta30 = venta30 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(6L)) {
                    venta45 = venta45 + item.getCantidad();
                }
            }
        }

        ventaProducto10.setText(String.valueOf(venta10));
        ventaProducto12.setText(String.valueOf(venta12));
        ventaProducto15.setText(String.valueOf(venta15));
        ventaProducto15me.setText(String.valueOf(venta15me));
        ventaProducto30.setText(String.valueOf(venta30));
        ventaProducto45.setText(String.valueOf(venta45));
    }

    private void cargarVentasEnvases(List<Movimiento> movimientos) {
        Integer venta10 = 0;
        Integer venta12 = 0;
        Integer venta15 = 0;
        Integer venta15me = 0;
        Integer venta30 = 0;
        Integer venta45 = 0;

        for (Movimiento mov: movimientos) {
            for (ItemMovimiento item: mov.getItems()) {
                if (item.getEnvase().getId().equals(7L) && item.getCantidad() > 0) {
                    venta10 = venta10 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(8L) && item.getCantidad() > 0) {
                    venta12 = venta12 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(9L) && item.getCantidad() > 0) {
                    venta15 = venta15 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(10L) && item.getCantidad() > 0) {
                    venta15me = venta15me + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(11L) && item.getCantidad() > 0) {
                    venta30 = venta30 + item.getCantidad();
                }

                if (item.getEnvase().getId().equals(12L) && item.getCantidad() > 0) {
                    venta45 = venta45 + item.getCantidad();
                }
            }
        }

        ventaEnvase10.setText(String.valueOf(venta10));
        ventaEnvase12.setText(String.valueOf(venta12));
        ventaEnvase15.setText(String.valueOf(venta15));
        ventaEnvase15me.setText(String.valueOf(venta15me));
        ventaEnvase30.setText(String.valueOf(venta30));
        ventaEnvase45.setText(String.valueOf(venta45));
    }

    private void cargarComprasEnvases(List<Movimiento> movimientos) {
        Integer compra10 = 0;
        Integer compra12 = 0;
        Integer compra15 = 0;
        Integer compra15me = 0;
        Integer compra30 = 0;
        Integer compra45 = 0;

        for (Movimiento mov: movimientos) {
            for (ItemMovimiento item: mov.getItems()) {
                if (item.getEnvase().getId().equals(7L) && item.getCantidad() < 0) {
                    compra10 = compra10 + (item.getCantidad() * -1);
                }

                if (item.getEnvase().getId().equals(8L) && item.getCantidad() < 0) {
                    compra12 = compra12 + (item.getCantidad() * -1);
                }

                if (item.getEnvase().getId().equals(9L) && item.getCantidad() < 0) {
                    compra15 = compra15 + (item.getCantidad() * -1);
                }

                if (item.getEnvase().getId().equals(10L) && item.getCantidad() < 0) {
                    compra15me = compra15me + (item.getCantidad() * -1);
                }

                if (item.getEnvase().getId().equals(11L) && item.getCantidad() < 0) {
                    compra30 = compra30 + (item.getCantidad() * -1);
                }

                if (item.getEnvase().getId().equals(12L) && item.getCantidad() < 0) {
                    compra45 = compra45 + (item.getCantidad() * -1);
                }
            }
        }

        compraEnvase10.setText(String.valueOf(compra10));
        compraEnvase12.setText(String.valueOf(compra12));
        compraEnvase15.setText(String.valueOf(compra15));
        compraEnvase15me.setText(String.valueOf(compra15me));
        compraEnvase30.setText(String.valueOf(compra30));
        compraEnvase45.setText(String.valueOf(compra45));
    }

    private boolean controlarStock10() {
        boolean control = true;

        Integer remanenteLleno10 = Integer.parseInt(inicLleno10.getText().toString())
                + Integer.parseInt(repoLleno10.getText().toString())
                - Integer.parseInt(ventaProducto10.getText().toString())
                - Integer.parseInt(rendAveriado10.getText().toString());
        Integer remanenteVacio10 = Integer.parseInt(ventaProducto10.getText().toString())
                + Integer.parseInt(repoVacio10.getText().toString());
        Integer controlLleno10 = Integer.parseInt(rendLleno10.getText().toString());
        Integer controlVacio10 = Integer.parseInt(rendVacio10.getText().toString())
                - Integer.parseInt(rendRetiro10.getText().toString())
                + Integer.parseInt(rendEntrega10.getText().toString())
                + Integer.parseInt(rendCambio10.getText().toString())
                - Integer.parseInt(compraEnvase10.getText().toString())
                + Integer.parseInt(ventaEnvase10.getText().toString());

        if ((remanenteLleno10 - controlLleno10 != 0) || (remanenteVacio10 - controlVacio10 != 0)) {
            control = false;
            control10.setText("NO");
            control10.setTextColor(ContextCompat.getColor(getContext(), R.color.estado0));
        } else {
            control10.setText("SI");
            control10.setTextColor(ContextCompat.getColor(getContext(), R.color.estado1));
        }

        return control;
    }

    private boolean controlarStock12() {
        boolean control = true;

        Integer remanenteLleno12 = Integer.parseInt(inicLleno12.getText().toString())
                + Integer.parseInt(repoLleno12.getText().toString())
                - Integer.parseInt(ventaProducto12.getText().toString())
                - Integer.parseInt(rendAveriado12.getText().toString());
        Integer remanenteVacio12 = Integer.parseInt(ventaProducto12.getText().toString())
                + Integer.parseInt(repoVacio12.getText().toString());
        Integer controlLleno12 = Integer.parseInt(rendLleno12.getText().toString());
        Integer controlVacio12 = Integer.parseInt(rendVacio12.getText().toString())
                - Integer.parseInt(rendRetiro12.getText().toString())
                + Integer.parseInt(rendEntrega12.getText().toString())
                - Integer.parseInt(compraEnvase12.getText().toString())
                + Integer.parseInt(ventaEnvase12.getText().toString());

        if ((remanenteLleno12 - controlLleno12 != 0) || (remanenteVacio12 - controlVacio12 != 0)) {
            control = false;
            control12.setText("NO");
            control12.setTextColor(ContextCompat.getColor(getContext(), R.color.estado0));
        } else {
            control12.setText("SI");
            control12.setTextColor(ContextCompat.getColor(getContext(), R.color.estado1));
        }

        return control;
    }

    private boolean controlarStock15() {
        boolean control = true;

        Integer remanenteLleno15 = Integer.parseInt(inicLleno15.getText().toString())
                + Integer.parseInt(repoLleno15.getText().toString())
                - Integer.parseInt(ventaProducto15.getText().toString())
                - Integer.parseInt(rendAveriado15.getText().toString());
        Integer remanenteVacio15 = Integer.parseInt(ventaProducto15.getText().toString())
                + Integer.parseInt(repoVacio15.getText().toString());
        Integer controlLleno15 = Integer.parseInt(rendLleno15.getText().toString());
        Integer controlVacio15 = Integer.parseInt(rendVacio15.getText().toString())
                - Integer.parseInt(rendRetiro15.getText().toString())
                + Integer.parseInt(rendEntrega15.getText().toString())
                - Integer.parseInt(rendCambio15.getText().toString())
                - Integer.parseInt(compraEnvase15.getText().toString())
                + Integer.parseInt(ventaEnvase15.getText().toString());

        if ((remanenteLleno15 - controlLleno15 != 0) || (remanenteVacio15 - controlVacio15 != 0)) {
            control = false;
            control15.setText("NO");
            control15.setTextColor(ContextCompat.getColor(getContext(), R.color.estado0));
        } else {
            control15.setText("SI");
            control15.setTextColor(ContextCompat.getColor(getContext(), R.color.estado1));
        }

        return control;
    }

    private boolean controlarStock15me() {
        boolean control = true;

        Integer remanenteLleno15me = Integer.parseInt(inicLleno15me.getText().toString())
                + Integer.parseInt(repoLleno15me.getText().toString())
                - Integer.parseInt(ventaProducto15me.getText().toString())
                - Integer.parseInt(rendAveriado15me.getText().toString());
        Integer remanenteVacio15me = Integer.parseInt(ventaProducto15me.getText().toString())
                + Integer.parseInt(repoVacio15me.getText().toString());
        Integer controlLleno15me = Integer.parseInt(rendLleno15me.getText().toString());
        Integer controlVacio15me = Integer.parseInt(rendVacio15me.getText().toString())
                - Integer.parseInt(rendRetiro15me.getText().toString())
                + Integer.parseInt(rendEntrega15me.getText().toString())
                - Integer.parseInt(compraEnvase15me.getText().toString())
                + Integer.parseInt(ventaEnvase15me.getText().toString());

        if ((remanenteLleno15me - controlLleno15me != 0) || (remanenteVacio15me - controlVacio15me != 0)) {
            control = false;
            control15me.setText("NO");
            control15me.setTextColor(ContextCompat.getColor(getContext(), R.color.estado0));
        } else {
            control15me.setText("SI");
            control15me.setTextColor(ContextCompat.getColor(getContext(), R.color.estado1));
        }

        return control;
    }

    private boolean controlarStock30() {
        boolean control = true;

        Integer remanenteLleno30 = Integer.parseInt(inicLleno30.getText().toString())
                + Integer.parseInt(repoLleno30.getText().toString())
                - Integer.parseInt(ventaProducto30.getText().toString())
                - Integer.parseInt(rendAveriado30.getText().toString());
        Integer remanenteVacio30 = Integer.parseInt(ventaProducto30.getText().toString())
                + Integer.parseInt(repoVacio30.getText().toString());
        Integer controlLleno30 = Integer.parseInt(rendLleno30.getText().toString());
        Integer controlVacio30 = Integer.parseInt(rendVacio30.getText().toString())
                - Integer.parseInt(rendRetiro30.getText().toString())
                + Integer.parseInt(rendEntrega30.getText().toString())
                - Integer.parseInt(compraEnvase30.getText().toString())
                + Integer.parseInt(ventaEnvase30.getText().toString());

        if ((remanenteLleno30 - controlLleno30 != 0) || (remanenteVacio30 - controlVacio30 != 0)) {
            control = false;
            control30.setText("NO");
            control30.setTextColor(ContextCompat.getColor(getContext(), R.color.estado0));
        } else {
            control30.setText("SI");
            control30.setTextColor(ContextCompat.getColor(getContext(), R.color.estado1));
        }

        return control;
    }

    private boolean controlarStock45() {
        boolean control = true;

        Integer remanenteLleno45 = Integer.parseInt(inicLleno45.getText().toString())
                + Integer.parseInt(repoLleno45.getText().toString())
                - Integer.parseInt(ventaProducto45.getText().toString())
                - Integer.parseInt(rendAveriado45.getText().toString());
        Integer remanenteVacio45 = Integer.parseInt(ventaProducto45.getText().toString())
                + Integer.parseInt(repoVacio45.getText().toString());
        Integer controlLleno45 = Integer.parseInt(rendLleno45.getText().toString());
        Integer controlVacio45 = Integer.parseInt(rendVacio45.getText().toString())
                - Integer.parseInt(rendRetiro45.getText().toString())
                + Integer.parseInt(rendEntrega45.getText().toString())
                - Integer.parseInt(compraEnvase45.getText().toString())
                + Integer.parseInt(ventaEnvase45.getText().toString());

        if ((remanenteLleno45 - controlLleno45 != 0) || (remanenteVacio45 - controlVacio45 != 0)) {
            control = false;
            control45.setText("NO");
            control45.setTextColor(ContextCompat.getColor(getContext(), R.color.estado0));
        } else {
            control45.setText("SI");
            control45.setTextColor(ContextCompat.getColor(getContext(), R.color.estado1));
        }

        return control;
    }

    private void cerrar() {
        if (controlarStock10() == false || controlarStock12() == false || controlarStock15() == false || controlarStock15me() == false || controlarStock30() == false || controlarStock45() == false) {
            Toast.makeText(getContext(), "Existen diferencias de stock.", Toast.LENGTH_LONG).show();
        } else {
            new PostCierreAsyncTask().execute();
        }
    }

    @Override
    public android.support.v4.content.Loader<List<Movimiento>> onCreateLoader(int i, Bundle bundle) {
        String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/movimiento/findByHojaRutaEstado/" + hojaRuta.getId() + "/3";

        return new MovimientoLoader(getContext(), url);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<Movimiento>> loader, List<Movimiento> movimientos) {
        Integer ventaPro10 = 0;
        Integer ventaPro12 = 0;
        Integer ventaPro15 = 0;
        Integer ventaPro15me = 0;
        Integer ventaPro30 = 0;
        Integer ventaPro45 = 0;

        Integer ventaEnv10 = 0;
        Integer ventaEnv12 = 0;
        Integer ventaEnv15 = 0;
        Integer ventaEnv15me = 0;
        Integer ventaEnv30 = 0;
        Integer ventaEnv45 = 0;

        Integer compraEnv10 = 0;
        Integer compraEnv12 = 0;
        Integer compraEnv15 = 0;
        Integer compraEnv15me = 0;
        Integer compraEnv30 = 0;
        Integer compraEnv45 = 0;

        if (movimientos != null && !movimientos.isEmpty()) {
            cargarVentasProducto(movimientos);
            cargarVentasEnvases(movimientos);
            cargarComprasEnvases(movimientos);
        } else {
            ventaProducto10.setText(String.valueOf(ventaPro10));
            ventaProducto12.setText(String.valueOf(ventaPro12));
            ventaProducto15.setText(String.valueOf(ventaPro15));
            ventaProducto15me.setText(String.valueOf(ventaPro15me));
            ventaProducto30.setText(String.valueOf(ventaPro30));
            ventaProducto45.setText(String.valueOf(ventaPro45));

            ventaEnvase10.setText(String.valueOf(ventaEnv10));
            ventaEnvase12.setText(String.valueOf(ventaEnv12));
            ventaEnvase15.setText(String.valueOf(ventaEnv15));
            ventaEnvase15me.setText(String.valueOf(ventaEnv15me));
            ventaEnvase30.setText(String.valueOf(ventaEnv30));
            ventaEnvase45.setText(String.valueOf(ventaEnv45));

            compraEnvase10.setText(String.valueOf(compraEnv10));
            compraEnvase12.setText(String.valueOf(compraEnv12));
            compraEnvase15.setText(String.valueOf(compraEnv15));
            compraEnvase15me.setText(String.valueOf(compraEnv15me));
            compraEnvase30.setText(String.valueOf(compraEnv30));
            compraEnvase45.setText(String.valueOf(compraEnv45));
        }

        controlarStock10();
        controlarStock12();
        controlarStock15();
        controlarStock15me();
        controlarStock30();
        controlarStock45();
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<Movimiento>> loader) {

    }
}

