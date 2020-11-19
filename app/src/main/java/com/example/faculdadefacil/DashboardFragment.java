package com.example.faculdadefacil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.faculdadefacil.Utils.JsonParserVolley;
import com.example.faculdadefacil.Utils.TokenSave;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardFragment extends Fragment {

    private CardView carteirinha_btn, profile_btn, grade_btn;
    private CardView notas_btn, contato_btn, config_btn, sair_btn;
    private TextView userName;
    private Context context;
    private String token;
    private String url = "/api/v1/dashboard";

    public DashboardFragment() {
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

        View fragDash = inflater.inflate(R.layout.fragment_dashboard, container, false);
        userName = fragDash.findViewById(R.id.username);// Get id textview username.
        carteirinha_btn = fragDash.findViewById(R.id.carteirinha_img_button); // creating a CardView and assigning a value.
        profile_btn = fragDash.findViewById(R.id.dashboard_img_button); // creating a CardView and assigning a value.
        grade_btn = fragDash.findViewById(R.id.grade_img_button); // creating a CardView and assigning a value.
        notas_btn = fragDash.findViewById(R.id.notas_img_button); // creating a CardView and assigning a value.
        contato_btn = fragDash.findViewById(R.id.contato_img_button); // creating a CardView and assigning a value.
        config_btn = fragDash.findViewById(R.id.config_img_button); // creating a CardView and assigning a value.
        sair_btn = fragDash.findViewById(R.id.sair_img_button);

        carteirinha_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fragDash) {
                //Move para a tela atravez da referencia do Fragment na Main
                NavController navController = Navigation.findNavController(fragDash);
                navController.navigate(R.id.action_nav_home_to_nav_carterinha);

                //Coloca o estado de ativo campo do drawer
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_carterinha);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fragDash) {
                //Move para a tela atravez da referencia do Fragment na Main
                NavController navController = Navigation.findNavController(fragDash);
                navController.navigate(R.id.action_nav_home_to_perfilFragmente);

                //Coloca o estado de ativo campo do drawer
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_profile);
            }
        });

        grade_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fragDash) {
                //Move para a tela atravez da referencia do Fragment na Main
                NavController navController = Navigation.findNavController(fragDash);
                navController.navigate(R.id.action_nav_home_to_gradeFragment);

                //Coloca o estado de ativo campo do drawer
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_grade);
            }
        });

        notas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fragDash) {
                //Move para a tela atravez da referencia do Fragment na Main
                NavController navController = Navigation.findNavController(fragDash);
                navController.navigate(R.id.action_nav_home_to_notasFragment);

                //Coloca o estado de ativo campo do drawer
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_notas);
            }
        });

        contato_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fragDash) {
                //Move para a tela atravez da referencia do Fragment na Main
                NavController navController = Navigation.findNavController(fragDash);
                navController.navigate(R.id.action_nav_home_to_contatosFragment);

                //Coloca o estado de ativo campo do drawer
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_contato);
            }
        });

        config_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fragDash) {
                //Move para a tela atravez da referencia do Fragment na Main
                NavController navController = Navigation.findNavController(fragDash);
                navController.navigate(R.id.action_nav_home_to_configFragment);

                //Coloca o estado de ativo campo do drawer
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_configuration);
            }
        });

        sair_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fragDash) {
                TokenSave.setToken(context, "");
                startActivity(new Intent(context, LoginActivity.class));
            }
        });

        token = TokenSave.getToken(context);

        final JsonParserVolley jsonParserVolley = new JsonParserVolley(context);
        jsonParserVolley.addHeader("Authorization", "Bearer " + token);
        jsonParserVolley.executeRequest(Request.Method.GET, url, new JsonParserVolley.VolleyCallback() {
                    @Override
                    public void getResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String name = obj.getString("NomeAluno");
                            userName.setText(name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        return fragDash;
    }
}