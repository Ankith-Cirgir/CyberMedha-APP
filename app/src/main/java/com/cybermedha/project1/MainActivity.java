package com.cybermedha.project1;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;

import java.lang.annotation.Annotation;


public class MainActivity extends AppCompatActivity implements ExoPlayer.EventListener {

    private PlaybackStateListener playbackStateListener;

    PlayerView playerView;
    public static SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playbackStateListener = new PlaybackStateListener();

        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        playerView = findViewById(R.id.video_view);

        initializePlayer();
        hideSystemUi();

    }

    private void initializePlayer() {

        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        MediaItem mediaItem2 = MediaItem.fromUri("https://videofiles.glocaltv.net/video_intro.mp4"); //internship  https://cdn.viewliveevents.net/337008605/demo.weblive.viewliveevents.net/playlist.m3u8
        player.setMediaItem(mediaItem2);
        player.prepare();

        //MediaItem mediaItem = MediaItem.fromUri("https://cdn.viewliveevents.net/337008605/demo.weblive.viewliveevents.net/playlist.m3u8"); //french.tv  https://moctobpltc-i.akamaihd.net/hls/live/571329/eight/playlist.m3u8   https://cdn.viewliveevents.net/337008605/demo.weblive.viewliveevents.net/playlist.m3u8
        //player.setMediaItem(mediaItem);

        player.addListener(playbackStateListener);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();


    }


    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            player.removeListener(playbackStateListener);

            System.out.println("Play pos"+playbackPosition+"\nbuffered upto: "+player.getBufferedPosition());
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        initializePlayer();
        hideSystemUi();
    }

}