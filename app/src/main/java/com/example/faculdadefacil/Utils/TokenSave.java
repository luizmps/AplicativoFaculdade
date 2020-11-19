package com.example.faculdadefacil.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenSave {
    private final static String SHARED_PREF_NAME = "com.example.faculfacil.MyPref";
    private final static String TOKEN_KEY = "com.example.faculfacil.TOKEN_KEY";

    public static String getToken(Context c) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(TOKEN_KEY, "");
    }

    public static void setToken(Context c, String token) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }
}