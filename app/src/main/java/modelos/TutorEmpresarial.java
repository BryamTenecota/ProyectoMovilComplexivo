package modelos;

import java.util.List;

public class TutorEmpresarial {
    
    private String cargo;
    private Usuario usuario;
    private Empresa empresa;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    List<Object[]> usuarios;

    public List<Object[]> getUsuarios() {
        return usuarios;
    }

}
