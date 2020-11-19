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

public class CarteirinhaFragment extends Fragment {

    //Declara um atributo para guardar o context.
    private Context context;
    private TextView cart_name, cart_curso, cart_validade;
    private String token;
    private String url = "/api/v1/carteirinha";

    public CarteirinhaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragCart = inflater.inflate(R.layout.fragment_carteirinha, container, false);

        cart_name = fragCart.findViewById(R.id.cart_name);
        cart_curso = fragCart.findViewById(R.id.cart_curso);
        cart_validade = fragCart.findViewById(R.id.cart_validade);

        token = TokenSave.getToken(context);

        final JsonParserVolley jsonParserVolley = new JsonParserVolley(context);
        jsonParserVolley.addHeader("Authorization", "Bearer " + token);
        jsonParserVolley.executeRequest(Request.Method.GET, url, new JsonParserVolley.VolleyCallback() {
                    @Override
                    public void getResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String name = obj.getString("NomeAluno");
                            String curso = obj.getString("NomeCurso");
                            String validade = obj.getString("Validade");
                            cart_name.setText(name);
                            cart_curso.setText(curso);
                            cart_validade.setText(validade);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        return fragCart;
    }
}