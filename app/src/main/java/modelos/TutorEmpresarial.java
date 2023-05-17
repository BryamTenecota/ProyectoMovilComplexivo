package modelos;

import java.util.List;

public class TutorEmpresarial {

    private  String nombres;
    private String cargo;
    private String departamento;
    private boolean estado;
    private String numerocontacto;
    private String titulo;
    private Usuario usuario;
    private Empresa empresa;
    private List<Usuario> usuarios;  // Corrección: Lista de usuarios

    public Usuario getUsuario() {return usuario;}

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public String getNombresUsuario() {
        return usuario.getNombres();
    }

public class TutorEmpresarial {

    private List<Empresa> empresas;

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNumerocontacto() {
        return numerocontacto;
    }

    public void setNumerocontacto(String numerocontacto) {
        this.numerocontacto = numerocontacto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Usuario> getUsuarios() {  // Corrección: Getter para la lista de usuarios
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {  // Corrección: Setter para la lista de usuarios
        this.usuarios = usuarios;
    }
}
