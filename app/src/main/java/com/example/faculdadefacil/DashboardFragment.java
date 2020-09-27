package com.example.faculdadefacil;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;

public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragDash = inflater.inflate(R.layout.fragment_dashboard, container, false);
        CardView card_view = (CardView) fragDash.findViewById(R.id.carteirinha_img_button); // creating a CardView and assigning a value.

        card_view.setOnClickListener(new View.OnClickListener() {
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

        return fragDash;
    }
}