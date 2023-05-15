package Servicios;




import java.util.List;

import modelos.Empresa;
import modelos.TutorEmpresarial;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Servicios {
//    @GET("posts")
    @GET("listar")
    Call<List<Empresa>> getEmpresa();

    @GET("listar")
    Call<List<TutorEmpresarial>> getTutorEmpresarial();
}
