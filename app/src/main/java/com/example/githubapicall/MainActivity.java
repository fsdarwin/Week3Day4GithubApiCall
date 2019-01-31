package com.example.githubapicall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.githubapicall.pojos.Profile;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import android.widget.TextView;
import static com.example.githubapicall.Constants.GIT_USER;

public class MainActivity extends AppCompatActivity {
    TextView tvLogin;
    TextView tvUrl;
    TextView tvCreatedAt;
    TextView tvUpdatedAt;
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpHelper.asyncOkHttpApiCall(GIT_USER, this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event){
        profile = event.getProfileMessage();
        tvLogin = findViewById(R.id.tvLogin);
        tvUrl = findViewById(R.id.tvUrl);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        tvUpdatedAt = findViewById(R.id.tvUpdatedAt);
        tvLogin.setText(profile.getLogin());
        tvUrl.setText(profile.getUrl());
        tvCreatedAt.setText(profile.getCreatedAt());
        tvUpdatedAt.setText(profile.getUpdatedAt());

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}
