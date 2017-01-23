package com.designfreed.distribuidoras_app_stock.activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.designfreed.distribuidoras_app_stock.R;
import com.designfreed.distribuidoras_app_stock.adapters.CompraAdapter;
import com.designfreed.distribuidoras_app_stock.converters.DateConverter;
import com.designfreed.distribuidoras_app_stock.dialogs.DateDialog;
import com.designfreed.distribuidoras_app_stock.domain.Envase;
import com.designfreed.distribuidoras_app_stock.domain.EstadoEnvase;
import com.designfreed.distribuidoras_app_stock.domain.ItemMovimientoStock;
import com.designfreed.distribuidoras_app_stock.domain.MovimientoStock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompraActivity extends AppCompatActivity {
    private ListView itemsListView;
    private EditText txtFecha;
    private EditText txtNroComprobante;
    private EditText txtCantidad;
    private EditText txtPrecio;
    private Spinner cboEnvases;
    private Button btnAgregar;
    private Button btnIngresar;

    private List<Envase> envases = new ArrayList<>();
    private List<EstadoEnvase> estadosEnvase = new ArrayList<>();
    private List<ItemMovimientoStock> items = new ArrayList<>();

    private CompraAdapter compraAdapter;
    private ArrayAdapter<Envase> envaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        envaseAdapter = new ArrayAdapter<Envase>(this, android.R.layout.simple_dropdown_item_1line, envases);

        compraAdapter = new CompraAdapter(this, items);

        itemsListView = (ListView) findViewById(R.id.list);
        itemsListView.setAdapter(compraAdapter);

        txtFecha = (EditText) findViewById(R.id.fecha);
        txtFecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "Fecha");
                }
            }
        });
        txtNroComprobante = (EditText) findViewById(R.id.nro_comprobante);
        txtCantidad = (EditText) findViewById(R.id.cantidad);
        txtPrecio = (EditText) findViewById(R.id.precio);

        cboEnvases = (Spinner) findViewById(R.id.articulos);
        cboEnvases.setAdapter(envaseAdapter);

        btnAgregar = (Button) findViewById(R.id.add);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemMovimientoStock item = new ItemMovimientoStock();

                Envase env = (Envase) cboEnvases.getSelectedItem();

                Integer cantidad = Integer.valueOf(txtCantidad.getText().toString());
                Float costo = Float.valueOf(txtPrecio.getText().toString());

                item.setEnvase(env);
                item.setEstadoEnvase(estadosEnvase.get(0));
                item.setCantidad(cantidad);
                item.setCosto(costo);
                item.setComodatoGenerado(false);
                items.add(item);

                compraAdapter.notifyDataSetChanged();

                resetItemFields();
            }
        });

        btnIngresar = (Button) findViewById(R.id.ingresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovimientoStock movimiento = new MovimientoStock();

                movimiento.setFecha(new DateConverter().stringToDate(txtFecha.getText().toString()));
                movimiento.setHojaRuta(null);
                movimiento.setTipoMovimiento("Compra Producto");
                movimiento.setModulo("Stock");
                movimiento.setNroComprobante(txtNroComprobante.getText().toString());
                for (ItemMovimientoStock itemLleno: items) {
                    movimiento.getItems().add(itemLleno);

                    ItemMovimientoStock itemVacio = new ItemMovimientoStock();

                    itemVacio.setEnvase(itemLleno.getEnvase());
                    itemVacio.setEstadoEnvase(estadosEnvase.get(1));
                    itemVacio.setCantidad(itemLleno.getCantidad() * -1);
                    itemVacio.setCosto(itemLleno.getCosto());
                    itemVacio.setComodatoGenerado(false);

                    movimiento.getItems().add(itemVacio);
                }

                new PostCompraTask().execute(movimiento);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new LoadEnvasesTask().execute();
            new LoadEstadosEnvaseTask().execute();
        } else {

        }
    }

    private class LoadEnvasesTask extends AsyncTask<Void, Void, List<Envase>> {
        @Override
        protected List<Envase> doInBackground(Void... params) {
            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/envase/list";

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

            envaseAdapter.clear();
            envaseAdapter.addAll(envases);
        }
    }

    private class LoadEstadosEnvaseTask extends AsyncTask<Void, Void, List<EstadoEnvase>> {
        @Override
        protected List<EstadoEnvase> doInBackground(Void... params) {
            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/estadoEnvase/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                EstadoEnvase[] estadosEnvase = restTemplate.getForObject(url, EstadoEnvase[].class);

                return Arrays.asList(estadosEnvase);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<EstadoEnvase> result) {
            super.onPostExecute(result);

            estadosEnvase = result;
        }
    }

    private class PostCompraTask extends AsyncTask<MovimientoStock, Void, Boolean> {
        @Override
        protected Boolean doInBackground(MovimientoStock... params) {
            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/movimientoStock/add";

            MovimientoStock movimiento = params[0];

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<MovimientoStock> response = restTemplate.postForEntity(url, movimiento, MovimientoStock.class);

                return  (response.getStatusCode() == HttpStatus.OK);
            } catch (ResourceAccessException connectException) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                Toast.makeText(getApplicationContext(), "Ingreso satisfactorio", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Ingreso fallido", Toast.LENGTH_SHORT).show();
            }

            resetMovimientoFields();
        }
    }

    private void resetItemFields() {
        cboEnvases.setSelection(0);
        txtCantidad.setText("");
        txtPrecio.setText("");
    }

    private void resetMovimientoFields() {
        txtFecha.setText("");
        txtNroComprobante.setText("");
        items.clear();
        compraAdapter.notifyDataSetChanged();
    }
}
