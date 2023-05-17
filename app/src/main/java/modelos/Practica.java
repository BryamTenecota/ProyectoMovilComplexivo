package modelos;

import java.util.List;

public class Practica {


    private String horaInicio;
    private String horaSalida;

    private List<TutorEmpresarial> tutorEmpresarials;
    private List<Usuario> usuarios;

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
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
