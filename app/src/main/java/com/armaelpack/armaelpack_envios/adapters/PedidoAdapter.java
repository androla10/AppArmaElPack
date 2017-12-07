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
import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Pedido;

import java.util.List;

/**
 * Created by ADRIAN on 06/12/2017.
 */

public class PedidoAdapter extends ArrayAdapter<Pedido> {
    private List<Pedido> miListaPedidos;


    public PedidoAdapter(@NonNull Context context, @NonNull List<Pedido> lista) {
        super(context, R.layout.detalle_lista_pedidos, lista);
        miListaPedidos =lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = convertView;
        if (view==null){
            view = inflater.inflate(R.layout.detalle_lista_pedidos,null);
        }

        ImageView imEstadoPedido = (ImageView) view.findViewById(R.id.imEstadoPedido);
        TextView tvNombreClientePedido = view.findViewById(R.id.tvNom)


        return super.getView(position, convertView, parent);
    }
}
