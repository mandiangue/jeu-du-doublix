package com.example.bestappli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class recompenseActivity extends AppCompatActivity {
WebView webView;
Button bouton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recompense);
        bouton = (Button) findViewById(R.id.btnretour);
        webView= findViewById(R.id.webview);
        webView.setWebViewClient(new Mybrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://santesaliou.net/jeu/main/whiteControllers.html");

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             retour();

            }
        });
    }
    void retour(){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
    private class Mybrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}