package com.alaqsa.edu.ps.staffservices.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.alaqsa.edu.ps.staffservices.R;
import com.alaqsa.edu.ps.staffservices.activity.LoginActivity;
import com.alaqsa.edu.ps.staffservices.activity.MainActivity;
import com.alaqsa.edu.ps.staffservices.databinding.ActivitySplashBinding;
import com.alaqsa.edu.ps.staffservices.service.MyService;
import com.alaqsa.edu.ps.staffservices.temp.TemporaryData;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private Animation anim;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);


        Intent serviceIntent = new Intent(getBaseContext(), MyService.class);
        startService(serviceIntent);

        // Declare an imageView to show the animation.
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

//        TemporaryData temporaryData =new TemporaryData(this);
//        temporaryData.dataGeneration();

        // Create the animation.
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // MainActivity.class is the activity to go after showing the splash screen.
                if (sharedPreferences.getBoolean("shared_prefs", true))
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                else startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.splashImageView.startAnimation(anim);
        binding.splashTextView.startAnimation(anim);
    }
}