package com.example.movilprepracticasprofesional;

import androidx.fragment.app.Fragment;

public class ConveniosFragment extends Fragment {
   /* private TextView mjsonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_respuesta, container, false);

        mjsonText = rootView.findViewById(R.id.jsonText);
        MostrarJson();

        return rootView;
    }

    private void  MostrarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.39:8080/api/convenio/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servie= retrofit.create(Servicios.class);
        Call<List<Convenio>> call= servie.getConvenio();

        call.enqueue(new Callback<List<Convenio>>() {
            @Override
            public void onResponse(Call<List<Convenio>> call, Response<List<Convenio>> response) {
                if(!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Convenio> lista= response.body();
                for(Convenio empresa: lista){
                    String content="";
                    content+="Descripcion: "+empresa.getDescripcion()+"\n";
                    content+="Fecha de elaboración: "+empresa.getFecha_elaboracion()+"\n";
                    content+="Número de convenio: "+empresa.getNumero_convenio()+"\n";
                    content+="Número ITV: "+empresa.getNumero_itv()+"\n";
                    mjsonText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Convenio>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }

        });
    }*/
}
