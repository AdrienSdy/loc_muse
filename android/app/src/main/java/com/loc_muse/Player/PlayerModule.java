package com.facebook.react.modules.player;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.media.MediaPlayer;
import android.media.AudioManager;
import android.net.Uri;
import java.io.IOException;

public class PlayerModule extends ReactContextBaseJavaModule {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String music;

    public PlayerModule(ReactApplicationContext reactContext) {
        super(reactContext);

        // On initialise le MediaPlayer et on le met dans une variable
        // de classe pour s'en servir dans les méthodes
        this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    //Obligatoire
    @Override
    public String getName() {
        return "Player";
    }

    //This annotations expose method at javascript
    @ReactMethod
    public void play() {
        if (this.mediaPlayer.isPlaying()){
            this.mediaPlayer.seekTo(this.mediaPlayer.getCurrentPosition());
        } 
        this.mediaPlayer.start();
    }


    @ReactMethod
    public void selectMusic(String url, Callback callback){
        if(this.music == url){
            if(this.mediaPlayer.isPlaying()){
                this.pause();
            }
        }
        this.music = url;
        this.mediaPlayer.reset();
        try {
            this.mediaPlayer.setDataSource(url);
            this.mediaPlayer.prepare();            
        } catch(IOException e){
            callback.invoke(e.getMessage());
        }
    }

    // Met la musique en pause
    @ReactMethod
    public void pause(Callback callback) {
        this.mediaPlayer.pause();
    }

    // Modifie le volume à partir de la valeur envoyée
    @ReactMethod
    public void changeVolume(double volume, Callback callback){
        float volumeFloat = (float) volume;
        this.mediaPlayer.setVolume(volumeFloat / 100, volumeFloat / 100);
    }
}