package com.david.designapps.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.david.designapps.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductosAdapter extends SimpleAdapter {


    public LayoutInflater inflater;
    public ProductosAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        inflater = LayoutInflater.from(context);


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);


        HashMap<String,Object> data = (HashMap<String, Object>) getItem(position);

        TextView txtNombreProducto = view.findViewById(R.id.txt_nombre_producto);
        TextView txtPrecioProducto = view.findViewById(R.id.txt_precio_producto);

        txtNombreProducto.setText((String) data.get("Nombre Producto"));
        txtPrecioProducto.setText((String) data.get("Precio"));


        return view;
    }
}
