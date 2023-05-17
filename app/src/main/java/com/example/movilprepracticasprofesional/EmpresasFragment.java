package com.example.movilprepracticasprofesional;

import androidx.fragment.app.Fragment;

public class EmpresasFragment extends Fragment {
    /*private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_respuesta, container, false);

        mjsonText = rootView.findViewById(R.id.jsonText);
        MostrarJson();

        return rootView;
    }

    private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/empresa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Empresa>> call= servie.getEmpresa();

        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Empresa> lista= response.body();
                for(Empresa empresa: lista){
                    String content="";
                    content+="Nombre: "+empresa.getNombreEmpresa()+"\n";
                    content+="RUC: "+empresa.getRucEmpresa()+"\n";
                    content+="Telefono: "+empresa.getNumeroTelefono()+"\n";
                    content+="Direccion: "+empresa.getDireccion()+"\n";
                    content+="Correo: "+empresa.getCorreo()+"\n";
                    mjsonText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }*/
}
