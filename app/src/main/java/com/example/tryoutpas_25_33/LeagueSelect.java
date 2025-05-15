package com.example.tryoutpas_25_33;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LeagueSelect extends Fragment {

    private Button buttonLaliga;
    private Button buttonPremier;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.league_fragment, container, false); // Make sure layout name is correct

        buttonLaliga = view.findViewById(R.id.laligabtn);
        buttonPremier = view.findViewById(R.id.premierbtn);

        buttonLaliga.setOnClickListener(v -> loadLeagueFragment("Spanish La Liga"));
        buttonPremier.setOnClickListener(v -> loadLeagueFragment("English Premier League"));

        return view;
    }

    private void loadLeagueFragment(String leagueName) {
        LeagueFragment leagueFragment = LeagueFragment.newInstance(leagueName);

        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, leagueFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
