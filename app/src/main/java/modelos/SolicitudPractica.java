package modelos;

public class SolicitudPractica {

    private String fechaEnvioSolicitud;
    private int numeroEstudiantes;
    private String nombreSolicitud;
    private String fechaAceptacion;

    private boolean estadoSolicitud;

    public String getFechaEnvioSolicitud() {
        return fechaEnvioSolicitud;
    }

    public void setFechaEnvioSolicitud(String fechaEnvioSolicitud) {
        this.fechaEnvioSolicitud = fechaEnvioSolicitud;
    }

    public int getNumeroEstudiantes() {
        return numeroEstudiantes;
    }

    public void setNumeroEstudiantes(int numeroEstudiantes) {
        this.numeroEstudiantes = numeroEstudiantes;
    }

    public String getNombreSolicitud() {
        return nombreSolicitud;
    }

    public void setNombreSolicitud(String nombreSolicitud) {
        this.nombreSolicitud = nombreSolicitud;
    }

    public String getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(String fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public boolean isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(boolean estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
}
