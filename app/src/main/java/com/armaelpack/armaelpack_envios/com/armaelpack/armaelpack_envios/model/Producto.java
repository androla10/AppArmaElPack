package com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model;

/**
 * Created by ADRIAN on 06/12/2017.
 */

public class Producto {

    private String codigoProducto;
    private String idProducto;
    private String nombrePro;
    private int cantidadPro;
    private double totalPro;

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
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
