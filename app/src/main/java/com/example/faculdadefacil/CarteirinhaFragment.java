package com.example.faculdadefacil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CarteirinhaFragment extends Fragment {

    public CarteirinhaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragCart = inflater.inflate(R.layout.fragment_carteirinha, container, false);

        TextView tv = (TextView) fragCart.findViewById(R.id.headtext);
        tv.setText("Luiz Adolfo Martins Paiva");
        return fragCart;
    }
}