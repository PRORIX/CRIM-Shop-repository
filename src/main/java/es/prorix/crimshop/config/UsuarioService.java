package es.prorix.crimshop.config;

import es.prorix.crimshop.backend.model.UsuarioModel;

public class UsuarioService {
    public static UsuarioModel usuarioActual;

    public static void setUsuarioActual(UsuarioModel usuario) {
        usuarioActual = usuario;
    }

    public static UsuarioModel getUsuarioActual() {
        return usuarioActual;
    }
}
