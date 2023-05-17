package Servicios;


import java.util.List;

import modelos.Convocatorias;
import modelos.Empresa;
import modelos.PersonaEmpresa;
import modelos.Convenio;
import modelos.DetalleConvenio;
import modelos.Empresa;
import modelos.Practica;
import modelos.TutorEmpresarial;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    @GET("listasoltuto/{idUsuario}")
    Call<List<Object[]>> getSolporTuto(@Path("idUsuario") int idUsuario);

    @GET("listasoltutoaceptadas/{idUsuario}")
    Call<List<Object[]>> getSolporTutoAceptadas(@Path("idUsuario") int idUsuario);

    @GET("listar")
    Call<List<TutorEmpresarial>> getTutorEmpresarial();

    @GET("listar")
    Call<List<Convocatorias>> getConvocatorias();

    @GET("listartuto/{idUsuario}")
    Call<List<Object[]>> getUsuariosPorTutorEmpresarial(@Path("idUsuario") int idUsuario);

    @GET("all")
    Call<List<Usuario>> getUsuarios();

    @GET("datos")
    Call<List<Object[]>> getInfoTutorEmpresarial();


}
