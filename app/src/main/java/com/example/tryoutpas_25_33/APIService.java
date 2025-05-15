package com.example.tryoutpas_25_33;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("api/v1/json/3/search_all_teams.php")
    Call<TeamResponse> getTeamsByLeague(@Query("l") String leagueName);
}
