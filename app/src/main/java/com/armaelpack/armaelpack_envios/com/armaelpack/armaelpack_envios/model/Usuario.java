package com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model;

/**
 * Created by ADRIAN on 25/11/2017.
 */

public class Usuario extends ResponseGeneric{

    private String username;
    private String nombreCompleto;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
}
