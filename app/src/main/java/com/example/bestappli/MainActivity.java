package com.example.bestappli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout layout;
    private Button pointaffiche, point, chance , demarrage;
  private int points= 0;
  private int chances = 5;
  private int affichepoints = 0;
  private int n;
  private int totalpoints ;
    private MediaPlayer media, medias , mediag, mediap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointaffiche = (Button)findViewById(R.id.affichepoints);
        point= (Button)findViewById(R.id.points);
        chance= (Button)findViewById(R.id.chances);
        demarrage= (Button)findViewById(R.id.demarrejeu);

          demarrage.setOnClickListener(this);
        this.media = MediaPlayer.create(getApplicationContext(), R.raw.bingo);
        this.medias = MediaPlayer.create(getApplicationContext(), R.raw.perdu);
        this.mediag = MediaPlayer.create(getApplicationContext(), R.raw.crienfant);
        this.mediap = MediaPlayer.create(getApplicationContext(), R.raw.beugle);

            this.point.setText(points + " POINT");
            this.chance.setText(chances + " CHANCES");
            pointaffiche.setText(affichepoints + "  POINT");
            pointaffiche.setTextSize(25);


        }



    public void onClick(View  view) {

        getNumb();
        getResultat();

    }


// Obtenir les numéros aléatoires
    void getNumb(){
        Random rand;
        rand = new Random();
        int n = rand.nextInt(1000);
        affichepoints =  n ;

        pointaffiche.setText(affichepoints + "  POINTS");


        if(affichepoints % 2 == 0 && affichepoints != 0){
            Animation animahoraire = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotationhoraire);
            pointaffiche.startAnimation(animahoraire);

            points = affichepoints;
            totalpoints += points;
            chances--;
            chance.setText( chances  +" CHANCES");
            point.setText(totalpoints + " POINTS");
            point.setTextColor(Color.WHITE);
            chance.setTextColor(Color.WHITE);

            pointaffiche.setText(points + " POINTS");
           pointaffiche.setTextColor(Color.parseColor("#ffffff"));
            pointaffiche.setTextSize(25);
            jouerSonB();

        }
        if(affichepoints % 2 ==1) {
            Animation animahoraire = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotationhoraire2);
            pointaffiche.startAnimation(animahoraire);
            points = affichepoints;

            pointaffiche.setText(" Perdu !! ");
            pointaffiche.setTextColor(Color.WHITE);
            pointaffiche.setTextSize(25);

            jouerSonP();

        }
        }

  // Résultat gagnant
        void getResultat(){

         if(totalpoints >= 3500){
             partieGagne();
             Animation animabaounce = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bouceanim);
             point.startAnimation(animabaounce);

           dialogueReussite();

             Animation animabounce = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bouceanim);
             point.startAnimation(animabounce);
             points= 0;
             totalpoints = 0;
             chances = 5;
             affichepoints= 0;
             this.point.setText(points + " POINT");
             this.chance.setText(chances + " CHANCES");
             pointaffiche.setText(affichepoints + "  POINT");
             pointaffiche.setTextColor(Color.BLACK);
             point.setTextColor(Color.BLACK);
             chance.setTextColor(Color.BLACK);



            }
  // Résultat perdant
           if(chances <= 0 || chances == 0){
               partiePerdante();
             Animation animablink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blinkanim);
             chance.startAnimation(animablink);
               dialogueEchec();
             points= 0;
             totalpoints = 0;
             chances = 5;
             affichepoints= 0;

             this.point.setText(points + " POINT");
             this.chance.setText(chances + " CHANCES");
             pointaffiche.setText(affichepoints + "  POINT");
             pointaffiche.setTextColor(Color.BLACK);
               point.setTextColor(Color.BLACK);
               chance.setTextColor(Color.BLACK);

         }
           if(chances == 1){
          this.chance.setText(chances + " CHANCE");
 }

        }
//son en cas de partie gagnante
    public void partieGagne(){
        mediag.start();
    }

    //son partie perdante
    public void partiePerdante(){
        mediap.start();
    }

    //Son en cas de réussite
      public void jouerSonB(){
        media.start();
}
    // Son en cas d'échec
    public void jouerSonP(){

        medias.start();
    }
// Couper le son
      public void stoperSon(){
      media.stop()  ;
      medias.stop();
    }
    //Redémarrer le son
    public void resetSon(){
        this.media = MediaPlayer.create(getApplicationContext(), R.raw.bingo);
        this.medias = MediaPlayer.create(getApplicationContext(), R.raw.perdu);
    }
    // Dialog en cas de réussite
    public void dialogueReussite(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)

                .setIcon(R.drawable.trophee)
                .setTitle("BRAVO !!")
        .setMessage("Félicitations vous avez gagné !! " + totalpoints + " points et vous avez droit à une récompense")
                .setPositiveButton("Voir ma récompense", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       recompense();
                    }
                }).setNegativeButton("Sortir du Jeu ?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);



    }

    //fonction récompense
    public void recompense(){
        Intent intent = new Intent(this, recompenseActivity.class);
        startActivity(intent);
    }
     //Dialog en cas d'échec
    public void dialogueEchec(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.perdu)
                .setTitle("GAME OVER !!")
                .setMessage("Vous avez obtenu " + totalpoints + " points qui sont insuffisants.Vous pouvez retenter votre chance ou sortir du jeu !!  ")
                .setPositiveButton("Je retente ma chance ?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setNegativeButton("Je sors du Jeu ?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(DialogInterface.BUTTON_POSITIVE).setAllCaps(false);
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setAllCaps(false);


    }
// Intégration de menuhautdroit
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuitem1:
                Toast.makeText(this,"Son désactivé avec succès", Toast.LENGTH_SHORT).show();
                stoperSon();

                return true;
            case R.id.menuitem2:
                Toast.makeText(this,"Son activé avec succès", Toast.LENGTH_SHORT).show();
              resetSon();
                return true;

        }
       return super.onOptionsItemSelected(item);
    }



}


