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

public class GradeFragment extends Fragment {

    private TextView materia1, dia1, professor;
    private String token;
    private Context context;
    private String url = "/api/v1/grade";

    public GradeFragment() {
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
        View fragGrade = inflater.inflate(R.layout.fragment_grade, container, false);
        materia1 = fragGrade.findViewById(R.id.Materia);
        dia1 = fragGrade.findViewById(R.id.dia);
        professor = fragGrade.findViewById(R.id.professor);

        token = TokenSave.getToken(context);

        final JsonParserVolley jsonParserVolley = new JsonParserVolley(context);
        jsonParserVolley.addHeader("Authorization", "Bearer " + token);
        jsonParserVolley.executeRequest(Request.Method.GET, url, new JsonParserVolley.VolleyCallback() {
                @Override
                public void getResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String name = obj.getString("NomeDisciplina");
                        String hora = obj.getString("Horario");
                        String prof = obj.getString("NomeProfessor");
                        materia1.setText(name);
                        dia1.setText("| "+ hora+" |");
                        professor.setText(prof);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        return fragGrade;
    }
}