package unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.prorix.crimshop.backend.model.UsuarioModel;

public class UsuarioModelTest {
     private UsuarioModel usuarioModel;

    @BeforeEach
    void setUp() {
        usuarioModel = new UsuarioModel("nombre", "contrasenia", "email");
    }

    @Test
    void testGetters() {
        assertEquals("nombre", usuarioModel.getNombreUsuario());
        assertEquals("contrasenia", usuarioModel.getContrasenia());
        assertEquals("email", usuarioModel.getEmail());
    }

    @Test
    void testSetters() {
        usuarioModel.setNombreUsuario("nuevoNombre");
        usuarioModel.setContrasenia("nuevaContrasenia");
        usuarioModel.setEmail("nuevoEmail");

        assertEquals("nuevoNombre", usuarioModel.getNombreUsuario());
        assertEquals("nuevaContrasenia", usuarioModel.getContrasenia());
        assertEquals("nuevoEmail", usuarioModel.getEmail());
    }

    @Test
    void testEquals() {
        UsuarioModel otroUsuarioModel = new UsuarioModel("nombre", "contrasenia", "email");
        assertEquals(usuarioModel, otroUsuarioModel);
    }

    @Test
    void testHashCode() {
        UsuarioModel otroUsuarioModel = new UsuarioModel("nombre", "contrasenia", "email");
        assertEquals(usuarioModel.hashCode(), otroUsuarioModel.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("nombre,contrasenia,email", usuarioModel.toString());
    }
}

