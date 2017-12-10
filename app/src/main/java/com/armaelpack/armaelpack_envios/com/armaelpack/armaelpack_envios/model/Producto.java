package com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model;

/**
 * Created by ADRIAN on 06/12/2017.
 */

public class Producto {

    private String codigoProducto;
    private int idPedido;
    private String nombrePro;
    private int cantidadPro;
    private double totalPro;

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public int getCantidadPro() {
        return cantidadPro;
    }

    public void setCantidadPro(int cantidadPro) {
        this.cantidadPro = cantidadPro;
    }

    public double getTotalPro() {
        return totalPro;
    }

    public void setTotalPro(double totalPro) {
        this.totalPro = totalPro;
    }
}
