package com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model;

/**
 * Created by ADRIAN on 06/12/2017.
 */

public class Pedido {
    private int idPedido;
    private String codPedido;
    private String nomCliente;
    private String ventaTotal;
    private String currency;
    private String currencyName;
    private String latitudDestrino;
    private String longitudDestino;
    private String fechEmitido;
    private String fechEntrega;
    private String celular;
    private String correo;
    private String estado;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(String ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getLatitudDestrino() {
        return latitudDestrino;
    }

    public void setLatitudDestrino(String latitudDestrino) {
        this.latitudDestrino = latitudDestrino;
    }

    public String getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(String longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechEmitido() {
        return fechEmitido;
    }

    public void setFechEmitido(String fechEmitido) {
        this.fechEmitido = fechEmitido;
    }

    public String getFechEntrega() {
        return fechEntrega;
    }

    public void setFechEntrega(String fechEntrega) {
        this.fechEntrega = fechEntrega;
    }
}
