package modelos;

import java.util.List;

public class Usuario {

    private int idUsuario;
    private String cedula;
    private String correo;
    private String contrasenia;
    private String nombres;
    private String carrera;
    //Roles
    private List<Roles> roles;
    //Atributo statico para traer usuarios
    public static String rol;
    //Encriptar contrasenia
//    public static boolean verificarPassword(String passwordIngresada, String hashBCrypt) {
//        return BCrypt.checkpw(passwordIngresada, hashBCrypt);
//    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
