package com.example.dell.livestreaming;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class watchlive extends Activity {
    private VideoView mVideoView;
    private String pathToFileOrUrl = "rtmp://192.168.43.130:1935/live/android_test";


    private ProgressDialog progDailog;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        if (!LibsChecker.checkVitamioLibs(this))
            return;

        setContentView(R.layout.watchlive_layout);

        mVideoView = (VideoView) findViewById(R.id.videoview);


        progDailog = new ProgressDialog(this);
        progDailog.setCancelable(false);
        progDailog.setMessage("Please wait");
        progDailog.show();

        mVideoView.setVideoPath(pathToFileOrUrl);

        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        //mVideoView.

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progDailog.dismiss();
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }}
