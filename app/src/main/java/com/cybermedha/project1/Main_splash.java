package com.cybermedha.project1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main_splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1500;

    Animation toptobottom, bottomtotop;

    TextView text;

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }


        bottomtotop = AnimationUtils.loadAnimation(this,R.anim.bottomtoup);

        toptobottom = AnimationUtils.loadAnimation(this,R.anim.toptobottom);

        text = (TextView) findViewById(R.id.text);

        logo = (ImageView) findViewById(R.id.logo);

        text.startAnimation(bottomtotop);

        logo.startAnimation(toptobottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Main_splash.this,MainActivity.class));
                finish();
            }
        },SPLASH_SCREEN);
    }

}
