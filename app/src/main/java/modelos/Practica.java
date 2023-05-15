package modelos;

import java.util.List;

public class Practica {

    private String horaInicio;
    private String horaFin;

    private List<TutorEmpresarial> tutorEmpresarials;
    private List<Usuario> usuarios;

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public List<TutorEmpresarial> getTutorEmpresarials() {
        return tutorEmpresarials;
    }

    public void setTutorEmpresarials(List<TutorEmpresarial> tutorEmpresarials) {
        this.tutorEmpresarials = tutorEmpresarials;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
