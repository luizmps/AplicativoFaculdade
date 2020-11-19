package com.example.faculdadefacil;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.faculdadefacil.Utils.JsonParserVolley;
import com.example.faculdadefacil.Utils.TokenSave;

import org.json.JSONException;
import org.json.JSONObject;

public class PerfilFragmente extends Fragment {

    private TextView nome, rg, cpf, email, nascimento, curso;
    private String token;
    private String url = "/api/v1/perfil";
    private Context context;

    public PerfilFragmente() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragPerfil = inflater.inflate(R.layout.fragment_perfil, container, false);

        nome = fragPerfil.findViewById(R.id.nomealuno);
        rg = fragPerfil.findViewById(R.id.rg);
        cpf = fragPerfil.findViewById(R.id.cpf);
        email = fragPerfil.findViewById(R.id.email);
        nascimento = fragPerfil.findViewById(R.id.nascimento);
        curso = fragPerfil.findViewById(R.id.curso);

        token = TokenSave.getToken(context);

        final JsonParserVolley jsonParserVolley = new JsonParserVolley(context);
        jsonParserVolley.addHeader("Authorization", "Bearer " + token);
        jsonParserVolley.executeRequest(Request.Method.GET, url, new JsonParserVolley.VolleyCallback() {
                @Override
                public void getResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String json1 = obj.getString("NomeAluno");
                        String json2 = obj.getString("NomeCurso");
                        String json3 = obj.getString("Rg");
                        String json4 = obj.getString("CPF");
                        String json5 = obj.getString("Email");
                        String json6 = obj.getString("DataNascimento");
                        nome.setText(json1);
                        rg.setText(json3);
                        cpf.setText(json4);
                        email.setText(json5);
                        nascimento.setText(json6);
                        curso.setText(json2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        return fragPerfil;
    }
}