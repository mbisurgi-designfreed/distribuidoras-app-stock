package com.designfreed.distribuidoras_app_stock.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_stock.R;
import com.designfreed.distribuidoras_app_stock.domain.ItemMovimientoStock;
import com.designfreed.distribuidoras_app_stock.utils.Utils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CompraAdapter extends ArrayAdapter<ItemMovimientoStock> {
    public CompraAdapter(Context context, List<ItemMovimientoStock> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_compras, parent, false);
        }

        final ItemMovimientoStock item = getItem(position);

        TextView articulo = (TextView) listItemView.findViewById(R.id.articulo);
        articulo.setText(item.getEnvase().toString());

        TextView cantidad = (TextView) listItemView.findViewById(R.id.cantidad);
        cantidad.setText(item.getCantidad().toString());

        TextView precio = (TextView) listItemView.findViewById(R.id.precio);
        precio.setText(Utils.formatSaldo(item.getCosto()));

        Button remover = (Button) listItemView.findViewById(R.id.remove);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(item);
            }
        });

        return listItemView;
    }
}
