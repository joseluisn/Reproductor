package com.joseluis.reproductorsonido;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


import java.lang.reflect.Field;

public class AudioService extends Service {
    public  MediaPlayer player;
    public int [] songs;
    public  Field[] fields;

    public AudioService() throws IllegalAccessException {
        fields=R.raw.class.getFields();
        songs = new int[fields.length];
        for(int i=0; i < fields.length; i++)
            songs[i] = fields[i].getInt(fields[i]);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            int song = Math.abs(MainActivity.getIdSong()%songs.length);
            player = MediaPlayer.create(this, songs[song]);
            player.start();
            MainActivity.name.setText(fields[song].getName());
            new Thread(){
                @Override
                public void run() {

                    while(player.isPlaying()) {
                        MainActivity.bar.setMax(player.getDuration());
                        MainActivity.bar.setProgress(player.getCurrentPosition());
                    }
                }
            }.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
