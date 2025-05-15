package com.example.tryoutpas_25_33;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<ModelTeam> teamList;
    private Context context;

    public TeamAdapter(List<ModelTeam> teamList) {
        this.teamList = teamList;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.itemliga, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        ModelTeam team = teamList.get(position);
        holder.textViewTeamName.setText(team.getStrTeam());
        holder.textViewStadium.setText(team.getStrStadium());
        Glide.with(holder.itemView.getContext())
                .load(team.getStrBadge())
                .into(holder.imageBadge);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTeamName, textViewStadium;
        ImageView imageBadge;
        public TeamViewHolder(View itemView) {
            super(itemView);
            textViewTeamName = itemView.findViewById(R.id.textViewTeamName);
            textViewStadium = itemView.findViewById(R.id.textViewStadium);
            imageBadge = itemView.findViewById(R.id.teamBadge);
        }
    }
}
