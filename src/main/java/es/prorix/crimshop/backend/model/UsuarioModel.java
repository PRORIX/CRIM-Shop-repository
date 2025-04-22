package es.prorix.crimshop.backend.model;
import java.util.Objects;

/**
 * Clase modelo de los usuarios de la app
 * @author prorix
 * @version 1.0.0
 */
public class UsuarioModel {
    
    private String nombreUsuario;
    private String contrasenia;
    private String email;

    /**
     * Constructor vacio
     */
    public UsuarioModel(){

    }

    /**
     * Contructor solamente con el nombre de usuario
     * @param nombreUsuario del usuario
     */
    public UsuarioModel(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }


    /**
     * Constructor con todos los parametros
     * @param nombre del usuario
     * @param nombreUsuario del usuario
     * @param contrasenia del usuario
     * @param email del usuario
     */
    public UsuarioModel(String nombreUsuario, String contrasenia, String email) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    // Getters y setters



    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UsuarioModel nombreUsuario(String nombreUsuario) {
        setNombreUsuario(nombreUsuario);
        return this;
    }

    public UsuarioModel contrasenia(String contrasenia) {
        setContrasenia(contrasenia);
        return this;
    }

    public UsuarioModel email(String email) {
        setEmail(email);
        return this;
    }


    // Metodo equals

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioModel)) {
            return false;
        }
        UsuarioModel usuarioModel = (UsuarioModel) o;
        return Objects.equals(nombreUsuario, usuarioModel.nombreUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario);
    }



    // Metodo toString

    @Override
    public String toString() {
        return getNombreUsuario() + "," + getContrasenia() + "," + getEmail();
    }
    
}
