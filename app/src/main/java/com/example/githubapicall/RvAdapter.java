package com.example.githubapicall;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.githubapicall.pojos.repos.Repo;
import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    ArrayList<com.example.githubapicall.pojos.repos.Repo> repoArrayList;
    public static final String TAG = "FRANK: ";
    //CallTask result;

    public RvAdapter(ArrayList<com.example.githubapicall.pojos.repos.Repo> repoArrayList) {
        this.repoArrayList = repoArrayList;
    }

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder viewHolder, int position) {

        Repo repo = repoArrayList.get(position);
        if (repo != null){
            viewHolder.setItemRepo(repo);
            String fullName = repo.getFullName();
            String teamsUrl = repo.getTeamsUrl();
            String hooksUrl = repo.getHooksUrl();
            Log.d(TAG, "onBindViewHolder: " + fullName + ", " + teamsUrl + ", " + hooksUrl);

            viewHolder.fullName.setText(fullName);
            viewHolder.teamsUrl.setText(teamsUrl);
            viewHolder.hooksUrl.setText(hooksUrl);
        }
    }

    @Override
    public int getItemCount() {
        return repoArrayList!= null ? repoArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fullName;
        TextView teamsUrl;
        TextView hooksUrl;
        Repo itemRepo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.tvFullName);
            teamsUrl = itemView.findViewById(R.id.tvTeamsUrl);
            hooksUrl = itemView.findViewById(R.id.tvHooksUrl);



        }

        public void setItemRepo (Repo itemRepo){
            this.itemRepo = itemRepo;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
