package Servicios;




import java.util.List;

import modelos.Empresa;
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
    Call<List<TutorEmpresarial>> getTutorEmpresarial();

    @GET("all")
    Call<List<Usuario>> getUsuarios();

    @GET("estadoxusuario/{idUsuario}")
    Call<Boolean> getEstadoPorUsuario(@Path("idUsuario") int idUsuario);

    @GET("convocatoriaxusuario/{nombre_carrera}")
    Call<Boolean> getConvocatoriaPorCarrera(@Path("nombre_carrera") String nombre_carrera);
}
