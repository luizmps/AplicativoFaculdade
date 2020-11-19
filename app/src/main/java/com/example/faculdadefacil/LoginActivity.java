package com.example.faculdadefacil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.example.faculdadefacil.Utils.JsonParserVolley;
import com.example.faculdadefacil.Utils.TokenSave;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private EditText email_ET, password_ET;
    private String email, password;
    private String url = "/auth/authenticate";
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String verifyToken = TokenSave.getToken(this);
        if(verifyToken != ""){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        Button btn = (Button)findViewById(R.id.LoginButton);
        email_ET = findViewById(R.id.RaAluno);
        password_ET = findViewById(R.id.SenhaAluno);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email_ET.getText().toString();
                password = password_ET.getText().toString();

                final JsonParserVolley jsonParserVolley = new JsonParserVolley(LoginActivity.this);
                jsonParserVolley.postRequest(email, password, Request.Method.POST, url, new JsonParserVolley.VolleyCallback() {
                        @Override
                        public void getResponse(String response) {
                            String res = response;
                            try {
                                JSONObject obj = new JSONObject(response);
                                token = obj.getString("token");
                                TokenSave.setToken(LoginActivity.this, token);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                );
            }
        });
    }

}