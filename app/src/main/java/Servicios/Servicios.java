package Servicios;




import java.util.List;

import modelos.Convenio;
import modelos.DetalleConvenio;
import modelos.Empresa;
import modelos.Practica;

import modelos.SolicitudPractica;
import modelos.TutorEmpresarial;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Servicios {
//    @GET("posts")
    @GET("listar")
    Call<List<Empresa>> getEmpresa();

    @GET("listar")
    Call<List<Practica>> getPractica();

    @GET("listar")
    Call<List<Convenio>> getConvenio();

    @GET("listar")
    Call<List<DetalleConvenio>> getDetalleConvenio();

    @GET("listar")
    Call<List<SolicitudPractica>> getSolicitudPractica();

    Call<List<TutorEmpresarial>> getTutorEmpresarial();

    @GET("all")
    Call<List<Usuario>> getUsuarios();
}
