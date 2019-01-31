package com.example.githubapicall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.githubapicall.pojos.repos.Repo;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;

import static com.example.githubapicall.Constants.GIT_REPO;

public class Activity2 extends AppCompatActivity {

    public static final String TAG = "FRANK: ";
    ArrayList<Repo> repoArrayList;
    RecyclerView recyclerView;
    RvAdapter rvAdapter;
    String jsonResponse;
    Gson gson;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        OkHttpHelper.repoAsyncOkHttpApiCall(GIT_REPO, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView = findViewById(R.id.rvAct2);
        recyclerView.setLayoutManager(layoutManager);



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent (RepoEvent event) {
        repoArrayList = new ArrayList<>(event.getReposMessage().getRepos());
        //rvAdapter.notifyDataSetChanged();
        Log.d(TAG, "onEvent: Made array " + repoArrayList.get(1).getFullName());
        rvAdapter = new RvAdapter(repoArrayList);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
