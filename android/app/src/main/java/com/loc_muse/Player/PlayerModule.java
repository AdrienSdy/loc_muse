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
    public void play(Callback callback) {
        String url = "https://www.mixcloud.com/skybrom1/the-skybrom-show-episode-1/";
        try {
            this.mediaPlayer.setDataSource(url);
        } catch(IOException e){
            callback.invoke(e.getMessage());
        }
        try {
            this.mediaPlayer.prepare(); // might take long! (for buffering, etc)
        } catch(IOException e){
            callback.invoke(e.getMessage());
        }

        this.mediaPlayer.start();


        // Local Music
        //MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_file_1);
        //mediaPlayer.start(); // no need to call prepare(); create() does that for you
        //Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public void setMusic(String url, Boolean startMusic, Callback callback){
        this.mediaPlayer.reset();
        // On transforme notre string en Uri pour être lu par le mediaPlayer
        Uri myUri = Uri.parse(url);

        try {
            this.mediaPlayer.setDataSource(getReactApplicationContext(), myUri);
        } catch(IOException e){
            callback.invoke(e.getMessage());
        }

        try {
            this.mediaPlayer.prepare();
        } catch(IOException e){
            callback.invoke(e.getMessage());
        }

        if (startMusic){
            this.mediaPlayer.start();
        }
    }

    // @ReactMethod indique que la méthode sera disponible dans react
    // Lance une musique ou la relance si on s'était mis en pause
 /*   @ReactMethod
    public void play(Callback callback) {
        if (this.mediaPlayer.isPlaying()){
            this.mediaPlayer.seekTo(this.mediaPlayer.getCurrentPosition());
            this.mediaPlayer.start();
        } else {
            this.mediaPlayer.start();
            callback.invoke("here I go");
        }
    }
*/
    // Met la musique en pause
    @ReactMethod
    public void pause(Callback callback) {
        this.mediaPlayer.pause();
        callback.invoke("paused");
    }

    // Modifie le volume à partir de la valeur envoyée
    @ReactMethod
    public void changeVolume(double volume, Callback callback){
        float volumeFloat = (float) volume;
        this.mediaPlayer.setVolume(volumeFloat / 100, volumeFloat / 100);
    }
}