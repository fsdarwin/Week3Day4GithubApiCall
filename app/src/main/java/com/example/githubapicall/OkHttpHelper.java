package com.example.githubapicall;

import android.content.Context;
import android.util.Log;

import com.example.githubapicall.pojos.Profile;
import com.example.githubapicall.pojos.repos.Repo;
import com.example.githubapicall.pojos.repos.Repos;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {

    public static final String TAG = "FRANK: ";

    public static void asyncOkHttpApiCall(String url, Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    jsonResponse = response.body().string();
                    Gson gson = new Gson();
                    Profile profile = gson.fromJson(jsonResponse, Profile.class);
                    String data = profile.getLogin() + " " + profile.getUrl() + " " + profile.getCreatedAt() + " " + profile.getUpdatedAt();
                    Log.d(TAG, "onResponse: " + data);
                    EventBus.getDefault().post(new MessageEvent(profile));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void repoAsyncOkHttpApiCall(String url, Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    jsonResponse = "{\"repos\":" + response.body().string() + "}";
                    Gson gson = new Gson();
                    Repos repos = gson.fromJson(jsonResponse, Repos.class);
                    String data = repos.getRepos().get(0).getFullName();
                    Log.d(TAG, "onResponse: " + data);
                    EventBus.getDefault().post(new RepoEvent(repos));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}