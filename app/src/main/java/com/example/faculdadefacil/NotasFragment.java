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


public class NotasFragment extends Fragment {

    //Declara um atributo para guardar o context.
    private Context context;
    private TextView materia1, nota1;
    private String token;
    private String url = "/api/v1/notas";

    public NotasFragment() {
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
        View fragNotas = inflater.inflate(R.layout.fragment_notas, container, false);

        materia1 = fragNotas.findViewById(R.id.materia1_notas);
        nota1 = fragNotas.findViewById(R.id.nota1_notas);

        token = TokenSave.getToken(context);

        final JsonParserVolley jsonParserVolley = new JsonParserVolley(context);
        jsonParserVolley.addHeader("Authorization", "Bearer " + token);
        jsonParserVolley.executeRequest(Request.Method.GET, url, new JsonParserVolley.VolleyCallback() {
                @Override
                public void getResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String materia1_ = obj.getString("NomeDisciplina");
                        String nota1_ = obj.getString("NotaN1");
                        materia1.setText(materia1_);
                        nota1.setText(nota1_);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        return fragNotas;
    }
}