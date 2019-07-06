package com.example.minrax.myapppp;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.SharedPreferences;

import java.util.Random;
public class MainActivity extends AppCompatActivity {

    public int r1;
    public int r2;
    public int result = 0;
    public long sttime = System.currentTimeMillis();
    public long timediff;
    public long record = 100;
    public long toprecord = 100;
    public int rounds =10;
    public String lang = "";
    public String wintextlabel;
    public String toprelabel;
    public String seclabel;
    public String roundslabel;
    public String timelabel;
    public String recordlabel;
    public String newgametext;
    public String choose;
    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSettings();
        setLang();
        pickRandomNumbers();
        loadTopRecord();
    }

    private void loadSettings() {
        //loading settings
        SharedPreferences prefs = this.getSharedPreferences("lang", Context.MODE_PRIVATE);
        lang = prefs.getString("key", "");
    }

    private void setLang() {
        if (lang.toString() == "bg") {
            //BG texts
            wintextlabel = "Ти победи!";
            toprelabel = "ТОП РЕКОРД: ";
            seclabel = " сек";
            roundslabel = "Точки: ";
            timelabel = "Време: ";
            recordlabel = "рекорд: ";
            newgametext = "НОВА ИГРА?";
            choose = "избери";
            title = "Избирайте по-голямото число";
        } else {
            //EN Texts
            wintextlabel = "You  Won!";
            toprelabel = "All time record: ";
            seclabel = " sec";
            roundslabel = "Points: ";
            timelabel = "Time: ";
            recordlabel = "Current record: ";
            newgametext = "NEW GAME?";
            choose = "choose";
            title = "Click on the biggest number";
        }
        TextView lango = findViewById(R.id.textView3);
        lango.setText(lang);
        TextView chooset = findViewById(R.id.textView1);
        chooset.setText(choose);
    }

    private void loadTopRecord() {
        //loading top record
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        toprecord = prefs.getLong("key", 0);
        TextView toprec = (TextView) findViewById(R.id.toprecord);
        toprec.setText(toprelabel + toprecord);
    }

    public void goToSettingsFun(View view) {
        startActivity(new Intent(MainActivity.this, settings.class));

    }

    private void pickRandomNumbers() {
        //generate random number
        Random rand = new Random();
        switch (r1 = rand.nextInt(100)) {
        }
        switch (r2 = rand.nextInt(100)) {
        }
        while (true) {
            if (r2 == r1) {
                r2 = rand.nextInt(100);
            } else {
                break;
            }
        }
        Button lbutt = (Button) findViewById(R.id.left_button);
        lbutt.setText(Integer.toString(r1));
        Button rbutt = (Button) findViewById(R.id.right_button);
        rbutt.setText(Integer.toString(r2));
        TextView recordv = (TextView) findViewById(R.id.recordview);
    }

    public void leftButton(View view) {
            if (result >= rounds) {
                TextView finres = (TextView) findViewById(R.id.finrestext);
                TextView timerview = (TextView) findViewById(R.id.timer);
                TextView recordv = (TextView) findViewById(R.id.recordview);
                TextView toprec = (TextView) findViewById(R.id.toprecord);
                Button newg = (Button) findViewById(R.id.buttonnewgame);
                newg.setVisibility(View.VISIBLE);
                newg.setText(newgametext);
                finres.setVisibility(View.VISIBLE);
                timerview.setVisibility(View.VISIBLE);
                finres.setText(wintextlabel);
                timediff = (System.currentTimeMillis() - sttime)/1000;
                timerview.setText(timelabel + timediff + seclabel);
                if (record > timediff) {
                    record = timediff;
                    recordv.setText(recordlabel + record);
                }
                if (record < toprecord) {
                    toprecord = record;
                    toprec.setText(toprelabel + toprecord);
                    //saving top record
                    SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("key", toprecord);
                    editor.commit();
                }
            } else {
                if (r1 >= r2) {
                    result++;
                } else {
                    result--;
                }
                TextView pt = (TextView) findViewById(R.id.points);
                pt.setText(roundslabel + result);
                pickRandomNumbers();
            }
    }
    public void rightButton(View view) {
            if (result >= rounds) {
                TextView finres = (TextView) findViewById(R.id.finrestext);
                TextView timerview = (TextView) findViewById(R.id.timer);
                TextView recordv = (TextView) findViewById(R.id.recordview);
                TextView toprec = (TextView) findViewById(R.id.toprecord);
                Button newg = (Button) findViewById(R.id.buttonnewgame);
                newg.setVisibility(View.VISIBLE);
                newg.setText(newgametext);
                finres.setVisibility(View.VISIBLE);
                timerview.setVisibility(View.VISIBLE);
                finres.setText(wintextlabel);
                timediff = (System.currentTimeMillis() - sttime)/1000;
                timerview.setText(timelabel + timediff + seclabel);
                if (record > timediff) {
                    record = timediff;
                    recordv.setText(recordlabel + record);
                }
                if (record < toprecord) {
                    toprecord = record;
                    toprec.setText(toprelabel + toprecord);
                    //setting top record
                    SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("key", toprecord);
                    editor.commit();
                }
            } else {
                if (r2 >= r1) {
                    result++;
                } else {
                    result--;
                }
                TextView pt = (TextView) findViewById(R.id.points);
                pt.setText(roundslabel + result);
                pickRandomNumbers();
            }
    }

    public void newGameButton(View view) {
        result = 0;
        pickRandomNumbers();
        TextView finres = (TextView) findViewById(R.id.finrestext);
        TextView timerview = (TextView) findViewById(R.id.timer);
        Button newg = (Button) findViewById(R.id.buttonnewgame);
        finres.setText("");
        timerview.setText("");
        newg.setVisibility(View.INVISIBLE);
        finres.setVisibility(View.INVISIBLE);
        timerview.setVisibility(View.INVISIBLE);

        sttime = System.currentTimeMillis();
        TextView pt = (TextView) findViewById(R.id.points);
        pt.setText(roundslabel + result);
    }
}