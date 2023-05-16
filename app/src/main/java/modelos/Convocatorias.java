package modelos;

import java.util.List;

public class Convocatorias {

    private Long idConvocatorias;

    private String nombreConvocatoria;

    private String fechaPublicacion;

    private String fechaExpiracion;

    private boolean estadoConvocatoria;

    public String getNombreConvocatoria() {return nombreConvocatoria;}

    public void setNombreConvocatoria(String nombreConvocatoria) {this.nombreConvocatoria = nombreConvocatoria;}

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {this.fechaPublicacion = fechaPublicacion;}

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {this.fechaExpiracion = fechaExpiracion;}

    public boolean isEstadoConvocatoria() {
        return estadoConvocatoria;
    }

    public void setEstadoConvocatoria(boolean estadoConvocatoria) {this.estadoConvocatoria = estadoConvocatoria;}
}
