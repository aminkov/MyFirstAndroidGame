package com.example.minrax.myapppp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class settings extends AppCompatActivity {

    public String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        loadSettings();
    }

    private void loadSettings() {
        //loading settings
        SharedPreferences prefs = this.getSharedPreferences("lang", Context.MODE_PRIVATE);
        lang = prefs.getString("key", "");
        Button langbut = findViewById(R.id.langbut);
        langbut.setText(lang);
    }

    public void changeLang(View view) {
        Button langbut = findViewById(R.id.langbut);
        if(lang == "en") {
            langbut.setText("BG");
            lang = "bg";
        } else {
            langbut.setText("EN");
            lang = "en";
        }
        //saving lang
        SharedPreferences prefs = this.getSharedPreferences("lang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("key", lang.toString());
        editor.commit();
    }

    public void backToGame(View view) {
        //going to the settings screen
        startActivity(new Intent(settings.this, MainActivity.class));
    }

}


