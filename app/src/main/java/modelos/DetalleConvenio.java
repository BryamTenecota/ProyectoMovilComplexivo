package modelos;

import java.util.List;
import java.util.ListIterator;

public class DetalleConvenio {


        private int iddetalleConvenio;
        private String nombre_carrera;
        private List<Convenio> convenio;

        private List<Empresa> empresa;


    public int getIddetalleConvenio() {
        return iddetalleConvenio;
    }

    public void setIddetalleConvenio(int iddetalleConvenio) {
        this.iddetalleConvenio = iddetalleConvenio;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public List<Convenio> getConvenio() {
        return convenio;
    }

    public void setConvenio(List<Convenio> convenio) {
        this.convenio = convenio;
    }

    public List<Empresa> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(List<Empresa> empresa) {
        this.empresa = empresa;
    }
}
