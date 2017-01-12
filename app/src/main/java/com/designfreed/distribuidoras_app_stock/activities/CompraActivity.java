package com.designfreed.distribuidoras_app_stock.activities;

import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.designfreed.distribuidoras_app_stock.R;
import com.designfreed.distribuidoras_app_stock.adapters.CompraAdapter;
import com.designfreed.distribuidoras_app_stock.dialogs.DateDialog;
import com.designfreed.distribuidoras_app_stock.domain.Envase;
import com.designfreed.distribuidoras_app_stock.domain.ItemMovimientoStock;
import com.designfreed.distribuidoras_app_stock.loaders.EnvaseLoader;

import java.util.ArrayList;
import java.util.List;

public class CompraActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Envase>> {
    private ListView itemsListView;
    private EditText txtFecha;
    private EditText txtNroComprobante;
    private EditText txtCantidad;
    private EditText txtPrecio;
    private Spinner cboEnvases;
    private Button btnAgregar;

    private List<Envase> envases = new ArrayList<>();
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
                item.setEstadoEnvase(null);
                item.setCantidad(cantidad);
                item.setCosto(costo);
                item.setComodatoGenerado(false);
                items.add(item);

                compraAdapter.notifyDataSetChanged();

                cboEnvases.setSelection(0);
                txtCantidad.setText("");
                txtPrecio.setText("");
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
//            progressBar.setVisibility(View.GONE);
//            emptyView.setText("No hay conexion a internet disponible");
        }
    }

    @Override
    public Loader<List<Envase>> onCreateLoader(int id, Bundle args) {
        String url = "http://192.168.0.9:8080/distribuidoras-backend/envase/list";

        return new EnvaseLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Envase>> loader, List<Envase> envases) {
        envaseAdapter.addAll(envases);
    }

    @Override
    public void onLoaderReset(Loader<List<Envase>> loader) {
        envaseAdapter.clear();
    }
}
