package com.armaelpack.armaelpack_envios;

import com.armaelpack.armaelpack_envios.com.armaelpack.armaelpack_envios.model.Usuario;

/**
 * Created by ADRIAN on 25/11/2017.
 */

class Control {
    private static final Control miControl = new Control();

    public static Control getMiInstancia() {

        return miControl;
    }

    public Usuario miUsuario;

    private Control() {
    }
}
