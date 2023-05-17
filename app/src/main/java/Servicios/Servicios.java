package Servicios;




import java.util.List;

import modelos.Convocatorias;
import modelos.Empresa;
import modelos.EstudiantesAprob;
import modelos.PersonaEmpresa;
import modelos.TutorEmpresarial;
import modelos.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Servicios {
//    @GET("posts")
    @GET("listar")
    Call<List<Empresa>> getEmpresa();

    @GET("listar")
    Call<List<TutorEmpresarial>> getTutorEmpresarial();

    @GET("listar")
    Call<List<Convocatorias>> getConvocatorias();

    @GET("all")
    Call<List<Usuario>> getUsuarios();

    @GET("listarAprob")
    Call<List<EstudiantesAprob>> getEstudianteAprob();

    @GET("datos")
    Call<List<TutorEmpresarial>> getTutorEmpresarialA();
}
