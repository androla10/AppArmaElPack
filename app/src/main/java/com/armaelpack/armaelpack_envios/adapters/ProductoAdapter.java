package com.armaelpack.armaelpack_envios.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.armaelpack.armaelpack_envios.R;
import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Producto;

import java.util.List;

/**
 * Created by ADRIAN on 06/12/2017.
 */

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private List<Producto> miProductos;

    public ProductoAdapter(@NonNull Context context, @NonNull List<Producto> lista) {
        super(context, R.layout.detalle_lista_productos, lista);
        miProductos=lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());

        View view = convertView;
        if (view==null){
            view = inflater.inflate(R.layout.detalle_lista_productos,null);
        }

        ImageView imViewProducto = view.findViewById(R.id.imProducto);
        TextView tvCodigoPro = view.findViewById(R.id.tvCodigoPro);
        TextView tvNombrePro=view.findViewById(R.id.tvNombreProductoPro);
        TextView tvCantidadProd = view.findViewById(R.id.tvCantidadPro);
        TextView tvTotalProd = view.findViewById(R.id.tvTotalProd);

        tvCodigoPro.setText(miProductos.get(position).getCodigoProducto());
        tvNombrePro.setText(miProductos.get(position).getNombrePro());
        tvCantidadProd.setText(String.valueOf(miProductos.get(position).getCantidadPro()));
        tvTotalProd.setText(String.valueOf(miProductos.get(position).getTotalPro()));

        return view;
    }
}
