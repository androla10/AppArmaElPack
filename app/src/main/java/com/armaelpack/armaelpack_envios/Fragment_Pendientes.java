package com.armaelpack.armaelpack_envios;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.toolbox.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.armaelpack.armaelpack_envios.adapters.PedidoAdapter;
import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Pedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Pendientes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_Pendientes extends Fragment {
    private OnFragmentInteractionListener mListener;


    private List<Pedido> lstPendientes = new ArrayList<>();
    private PedidoAdapter pedidoAdapter;
    private ListView lvPendientes;


    public Fragment_Pendientes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //se crear el view para que se pueda visualizar los datos en la pantalla.
        View view = inflater.inflate(R.layout.fragment_fragment__pendientes, container, false);


        lvPendientes=view.findViewById(R.id.lvEntregaPendiente);
        lvPendientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pedido pedido = new Pedido();
                pedido.setId(lstPendientes.get(i).getId());
                pedido.setCodigoPedido(lstPendientes.get(i).getCodigoPedido());
                pedido.setNomCliente(lstPendientes.get(i).getNomCliente());
                pedido.setTotalNeto(lstPendientes.get(i).getTotalNeto());
                pedido.setLatitud(lstPendientes.get(i).getLatitud());
                pedido.setLongitud(lstPendientes.get(i).getLongitud());
                pedido.setFechaEmitido(lstPendientes.get(i).getFechaEmitido());
                pedido.setFechaEntrega(lstPendientes.get(i).getFechaEntrega());
                pedido.setCelular(lstPendientes.get(i).getCelular());
                pedido.setCorreo(lstPendientes.get(i).getCorreo());
                pedido.setEstado(lstPendientes.get(i).getEstado());

                if(pedido != null){
                    Control.getMiInstancia().miPedidoActual=pedido;
                    Intent intent = new Intent(getContext(),DetalleEnvio.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(),"No se guardo ningun dato",Toast.LENGTH_SHORT).show();
                }


            }
        });
/*

        Button btnFragmentPrueba = view.findViewById(R.id.btnPruebaFragmemnt);
        btnFragmentPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),DetalleEnvio.class);
                startActivity(intent);
            }
        });

*/
        obtenerPendientes();
        return view;
    }

    public void obtenerPendientes(){
        String urlPendientes = "http://10.143.143.127:8080/Home/listadoPedido";

        int estadoPendiente = 1;
        try {
            System.out.print("Entro Al JsonObject");
            JSONObject object = new JSONObject();
            object.put("iEstado",estadoPendiente);

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.POST,
                    urlPendientes,
                    object,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            try {
                                if (response.length() > 0) {
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        Pedido ped = new Pedido();
                                        ped.setId(jsonObject.getInt("id"));
                                        ped.setCodigoPedido(jsonObject.getString("codigoPedido"));
                                        ped.setTotalNeto(jsonObject.getString("totalNeto"));
                                        ped.setLatitud(jsonObject.getString("latitud"));
                                        ped.setLongitud(jsonObject.getString("longitud"));
                                        ped.setFechaEntrega(jsonObject.getString("fechaRegistro"));
                                        ped.setCorreo(jsonObject.getString("correo"));
                                        ped.setCelular(jsonObject.getString("celular"));
                                        ped.setEstado(jsonObject.getInt("estado"));

                                        // Control.getMiInstancia().miPedidoActual=ped;
                                        lstPendientes.add(ped);
                                    }
                                    pedidoAdapter = new PedidoAdapter(getActivity().getApplicationContext(), lstPendientes);
                                    lvPendientes.setAdapter(pedidoAdapter);
                                }
                            } catch (JSONException j) {
                                j.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.getNetworkTimeMs();
                        }
                    }
            );
            requestQueue.add(jsonArrayRequest);
        }catch (Exception e){
                e.printStackTrace();
            }
    }

























    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
