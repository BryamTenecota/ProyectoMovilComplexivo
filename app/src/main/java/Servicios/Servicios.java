package Servicios;


import java.util.List;

import modelos.Convenio;
import modelos.Empresa;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Servicios {
//    @GET("posts")
    @GET("listar")
    Call<List<Empresa>> getEmpresa();

    @GET("all")
    Call<List<Usuario>> getUsuarios();

    @GET("estadoxusuario/{idUsuario}")
    Call<Boolean> getEstadoPorUsuario(@Path("idUsuario") int idUsuario);

    @GET("convocatoriaxusuario/{nombre_carrera}")
    Call<Boolean> getConvocatoriaPorCarrera(@Path("nombre_carrera") String nombre_carrera);

    @GET("listar")
    Call<List<Convenio>> getConvenio();

    @GET("listasoltuto/{idUsuario}")
    Call<List<Object[]>> getSolporTuto(@Path("idUsuario") int idUsuario);

    @GET("listasoltutoaceptadas/{idUsuario}")
    Call<List<Object[]>> getSolporTutoAceptadas(@Path("idUsuario") int idUsuario);


    @GET("listartuto/{idUsuario}")
    Call<List<Object[]>> getUsuariosPorTutorEmpresarial(@Path("idUsuario") int idUsuario);


    @GET("listaestudiante/{idUsuario}")
    Call<List<Object[]>> getUsuariosBytutoracademico(@Path("idUsuario") int idUsuario);

    @GET("convocatoriadisp/{nombre_carrera}")
    Call<List<Object[]>> getConvocatoriaDisp(@Path("nombre_carrera") String nombre_carrera);

    @GET("ListaTutoresC")
    Call<List<Object[]>> getTutoresEmpresariales();

    @GET("EstudiantesARespon/{idUsuario}")
    Call<List<Object[]>> getEstudiantesByrespo(@Path("idUsuario") int idUsuario);

    @GET("ListaConvocatoriasC")
    Call<List<Object[]>> getConvocatorias();

}
