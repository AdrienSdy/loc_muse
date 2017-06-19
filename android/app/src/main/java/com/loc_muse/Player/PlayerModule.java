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
    private MediaPlayer mediaPlayer;
    private String music;

    public PlayerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.music = "https://dc600.4shared.com/img/of-0R-KLca/3f89964f/dlink__2Fdownload_2Fof-0R-KLca_2FShape_5Fof_5FYou.mp3_3Fsbsr_3Dec425c2072614565758360a00515de5a9ac_26bip_3DODUuNjguMTM3LjEyNw_26lgfp_3D7200_26bip_3DODUuNjguMTM3LjEyNw/preview.mp3";
        this.mediaPlayer = new MediaPlayer();
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
        if(this.mediaPlayer.isPlaying()){
            this.mediaPlayer.seekTo(this.mediaPlayer.getCurrentPosition());
        } 
        this.mediaPlayer.start();
    }


    @ReactMethod
    public void selectMusic(String url, Callback callback){
        if(this.music == url){
            if(this.mediaPlayer.isPlaying()){
                this.pause();
            }else{
                this.play();      
            }
        }else{
            this.music = url;
            this.mediaPlayer.reset();
            try {
                this.mediaPlayer.setDataSource(url);
                this.mediaPlayer.prepare();            
            } catch(IOException e){
                callback.invoke(e.getMessage());
            }
            this.play();
        }
    }

    // Met la musique en pause
    @ReactMethod
    public void pause() {
        this.mediaPlayer.pause();
    }

    // Modifie le volume à partir de la valeur envoyée
    @ReactMethod
    public void changeVolume(double volume, Callback callback){
        float volumeFloat = (float) volume;
        this.mediaPlayer.setVolume(volumeFloat / 100, volumeFloat / 100);
    }
}