package com.joseluis.reproductorsonido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int idSong;
    public boolean isPlaying;
    public ImageButton play;
    public static TextView name;
    public static ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idSong = 0;
        isPlaying= false;
        play = findViewById(R.id.playBtn);
        name = findViewById(R.id.textView);
        bar = findViewById(R.id.progressBarSong);
    }


    public void start (View view){
       if(!isPlaying){
            Intent intent = new Intent(this, AudioService.class);
            startService(intent);
            isPlaying=true;
        }
        else {
            Intent intent = new Intent(this, AudioService.class);
            stopService(intent);
            isPlaying=false;
        }
    }

    public void next (View view){
        stop();
        this.idSong++;
        Intent intent = new Intent(this, AudioService.class);
        startService(intent);
        isPlaying=true;
    }


    public void prev (View view){
        stop();
        if(idSong >0) {
            idSong--;
        }
        Intent intent = new Intent(this, AudioService.class);
        startService(intent);
        isPlaying=true;
    }

    private void stop (){
        Intent intent = new Intent(this, AudioService.class);
        stopService(intent);
        isPlaying=false;
    }

    public static int getIdSong() {
        return idSong;
    }

}
