package com.example.faculdadefacil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.faculdadefacil.Utils.JsonParserVolley;
import com.example.faculdadefacil.Utils.TokenSave;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Variaveis
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private TextView navUsername;
    private String token;
    private String url = "/api/v1/dashboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Remove native NightTheme On App
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);

        //Import Navigation and Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //Controle de Telas Fragment
        navigationView.bringToFront();
        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Drawer header name
        View headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.drawer_name);

        token = TokenSave.getToken(this);

        final JsonParserVolley jsonParserVolley = new JsonParserVolley(this);
        jsonParserVolley.addHeader("Authorization", "Bearer " + token);
        jsonParserVolley.executeRequest(Request.Method.GET, url,new JsonParserVolley.VolleyCallback() {
                @Override
                public void getResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String name = obj.getString("NomeAluno");
                        navUsername.setText(name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        );



    }

}