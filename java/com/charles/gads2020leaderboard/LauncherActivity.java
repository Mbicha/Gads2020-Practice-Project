package com.charles.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ActionMode;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LauncherActivity extends AppCompatActivity {
    private static int LAUNCHER_SCREEN_TIMEOUT = 2000;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_launcher);

        mImageView = findViewById(R.id.louncerImg);

        fadeAnim();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, Gads2020Activity.class);
                startActivity(intent);
                finish();
            }
        }, LAUNCHER_SCREEN_TIMEOUT);

    }


    private void fadeAnim() {
        Animation fadeSlowly = new AlphaAnimation(1, 0);
        fadeSlowly.setInterpolator(new AccelerateInterpolator());
        fadeSlowly.setStartOffset(1200);
        fadeSlowly.setDuration(800);

        mImageView.setAnimation(fadeSlowly);
    }
}