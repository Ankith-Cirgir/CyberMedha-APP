package com.cybermedha.project1;

import android.content.Context;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;

import static android.content.ContentValues.TAG;
import static com.cybermedha.project1.MainActivity.player;

public class PlaybackStateListener implements  Player.EventListener {


    @Override
    public void onPlaybackStateChanged(int playbackState) {
        String stateString;
        switch (playbackState) {
            case ExoPlayer.STATE_IDLE:
                MediaItem mediaItem = MediaItem.fromUri("https://videofiles.glocaltv.net/video_intro.mp4"); //internship  https://cdn.viewliveevents.net/337008605/demo.weblive.viewliveevents.net/playlist.m3u8
                player.setMediaItem(mediaItem);
                player.prepare();
                stateString = "ExoPlayer.STATE_IDLE      -";
                break;
            case ExoPlayer.STATE_BUFFERING:
                stateString = "ExoPlayer.STATE_BUFFERING -";
                break;
            case ExoPlayer.STATE_READY:
                stateString = "ExoPlayer.STATE_READY     -";
                break;
            case ExoPlayer.STATE_ENDED:
                MediaItem mediaItem1 = MediaItem.fromUri("https://cdn.viewliveevents.net/337008605/demo.weblive.viewliveevents.net/playlist.m3u8"); //french.tv  https://moctobpltc-i.akamaihd.net/hls/live/571329/eight/playlist.m3u8   https://cdn.viewliveevents.net/337008605/demo.weblive.viewliveevents.net/playlist.m3u8
                player.setMediaItem(mediaItem1);
                player.prepare();
                stateString = "ExoPlayer.STATE_ENDED     -";
                break;
            default:
                stateString = "UNKNOWN_STATE             -";
                break;
        }
        Log.d(TAG, "changed state to " + stateString);
    }
}