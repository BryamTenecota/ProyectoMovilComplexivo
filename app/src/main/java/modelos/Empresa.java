package modelos;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

public class Empresa implements Serializable {
//    private int userId;
//    private int id;
//    private String title;
//    private String body;

    private int idempresa;
    private String nombreempresa;
    private String numerotelefono;
    private String correo;
    private String descripcion;



    public Empresa(int idempresa, String nombreempresa, String numerotelefono, String correo, String descripcion) {
        this.idempresa = idempresa;
        this.nombreempresa = nombreempresa;
        this.numerotelefono = numerotelefono;
        this.correo = correo;
        this.descripcion = descripcion;
    }



    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public String getNumerotelefono() {
        return numerotelefono;
    }

    public void setNumerotelefono(String numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
