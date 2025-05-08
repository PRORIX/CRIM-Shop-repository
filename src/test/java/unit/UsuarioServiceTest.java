package unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.prorix.crimshop.backend.model.UsuarioModel;
import es.prorix.crimshop.config.UsuarioService;

public class UsuarioServiceTest {
    
    UsuarioModel usuarioActual;

    @BeforeEach
    void setUp(){
        usuarioActual = new UsuarioModel("nombre", "contrasenia", "email");
    }

    @Test
    void setAndGetUsuarioActualTest(){
        UsuarioService.setUsuarioActual(usuarioActual);
        assertEquals(new UsuarioModel("nombre", "contrasenia", "email"), UsuarioService.getUsuarioActual());
    }
}
