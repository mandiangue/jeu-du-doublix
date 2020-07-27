package com.example.bestappli;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
Button boutonvue;
Button btnsortie;
ImageView flecheview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

     flecheview = (ImageView) findViewById(R.id.flecheview);
        Animation animablink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blinkanimfleche);
        flecheview.startAnimation(animablink);
        boutonvue = (Button)findViewById(R.id.entrenjeu);

        boutonvue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity();
            }
        });

    }
    @Override()
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.sortieappli)
               .setTitle("Quitter l'application?")
                .setMessage("Voulez-vous vraiment quitter l'appli ? ")
                .setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SplashActivity.super.onBackPressed();

                    }
                }).setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }



    public void openActivity(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

}