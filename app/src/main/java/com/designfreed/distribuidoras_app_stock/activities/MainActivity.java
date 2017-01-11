package com.designfreed.distribuidoras_app_stock.activities;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_stock.R;
import com.designfreed.distribuidoras_app_stock.adapters.HojaRutaAdapter;
import com.designfreed.distribuidoras_app_stock.converters.DateConverter;
import com.designfreed.distribuidoras_app_stock.domain.HojaRuta;
import com.designfreed.distribuidoras_app_stock.loaders.HojaRutaLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<HojaRuta>> {
    private TextView emptyView;
    private ListView hojasListView;
    private ProgressBar progressBar;

    private HojaRutaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyView = (TextView) findViewById(R.id.empty);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        adapter = new HojaRutaAdapter(this, new ArrayList<HojaRuta>());

        hojasListView = (ListView) findViewById(R.id.list);
        hojasListView.setEmptyView(emptyView);
        hojasListView.setAdapter(adapter);
        hojasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HojaRuta hoja = (HojaRuta) parent.getItemAtPosition(position);


            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            progressBar.setVisibility(View.GONE);
            emptyView.setText("No hay conexion a internet disponible");
        }
    }

    @Override
    public Loader<List<HojaRuta>> onCreateLoader(int id, Bundle args) {
        Long fecha = new DateConverter().dateToLong(new Date());

        String url = "http://localhost:8080/distribuidoras-backend/hojaRuta/findByFecha/" + fecha;

        return new HojaRutaLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<HojaRuta>> loader, List<HojaRuta> hojaRutas) {
        adapter.clear();

        if (hojaRutas != null && !hojaRutas.isEmpty()) {
            emptyView.setText("");
            adapter.addAll(hojaRutas);
        } else {
            emptyView.setText("No existen hojas de rutas para el dia de hoy");
        }

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<HojaRuta>> loader) {
        adapter.clear();
    }
}