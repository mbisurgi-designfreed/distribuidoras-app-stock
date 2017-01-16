package com.designfreed.distribuidoras_app_stock.activities;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.designfreed.distribuidoras_app_stock.R;
import com.designfreed.distribuidoras_app_stock.domain.Carga;
import com.designfreed.distribuidoras_app_stock.loaders.CargaLoader;
import com.designfreed.distribuidoras_app_stock.pagers.CargaPagerAdapter;

import java.util.List;

public class CargaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Carga>> {
    private static final String SERVICE_URL = "http://bybgas.dyndns.org:8080/StockService/services/stockService/getCargaByHojaRuta?";
    private Long hojaRutaId;
    private String chofer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);

        hojaRutaId = (Long) getIntent().getSerializableExtra("id");
        chofer = getIntent().getStringExtra("chofer");

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {

        }
    }

    @Override
    public Loader<List<Carga>> onCreateLoader(int i, Bundle bundle) {
        return new CargaLoader(this, SERVICE_URL, hojaRutaId);
    }

    @Override
    public void onLoadFinished(Loader<List<Carga>> loader, List<Carga> cargas) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        CargaPagerAdapter pagerAdapter = new CargaPagerAdapter(getSupportFragmentManager(), hojaRutaId, chofer, cargas);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onLoaderReset(Loader<List<Carga>> loader) {

    }
}

