package com.example.tryoutpas_25_33;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeagueFragment extends Fragment {

    private List<ModelTeam> teamList = new ArrayList<>();
    private TeamAdapter adapter;
    private RecyclerView recyclerView;
    private String leagueName;

    public static LeagueFragment newInstance(String leagueName) {
        LeagueFragment fragment = new LeagueFragment();
        Bundle args = new Bundle();
        args.putString("LEAGUE_NAME", leagueName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.league_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            leagueName = getArguments().getString("LEAGUE_NAME", "English Premier League");
        } else {
            leagueName = "English Premier League";
        }

        recyclerView = view.findViewById(R.id.rvLiga);

        adapter = new TeamAdapter(teamList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        loadTeams();
    }

    private void loadTeams() {
        recyclerView.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<TeamResponse> call = api.getTeamsByLeague(leagueName);

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTeams() != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    teamList.clear();
                    teamList.addAll(response.body().getTeams());
                    adapter.notifyDataSetChanged();
                } else {
                    showError("No teams found or error in response");
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                showError("Failed to load teams: " + t.getMessage());
            }
        });
    }

    private void showError(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
