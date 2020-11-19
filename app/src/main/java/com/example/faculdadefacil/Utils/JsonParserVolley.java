package com.example.faculdadefacil.Utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class JsonParserVolley {

    final String contentType = "application/json; charset=utf-8";
    String baseURL = "http://adolphwittel.ddns.net:9009";
    Context context;
    RequestQueue requestQueue;
    String jsonresponse;

    private Map<String, String> header;

    public JsonParserVolley(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        header = new HashMap<>();

    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    public void executeRequest(int method, String url, final VolleyCallback callback) {

        StringRequest stringRequest = new StringRequest(method, baseURL + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonresponse = response;
                Log.e("RES", " res::" + jsonresponse);
                callback.getResponse(jsonresponse);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void postRequest(final String email, final String password, int method, String url, final VolleyCallback callback){
        StringRequest stringRequest = new StringRequest(method, baseURL + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonresponse = response;
                Log.e("RES", " res::" + jsonresponse);
                callback.getResponse(jsonresponse);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Handle error if you want
            }
        }) {
            //Pass Post Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public interface VolleyCallback {
        public void getResponse(String response);
    }
}