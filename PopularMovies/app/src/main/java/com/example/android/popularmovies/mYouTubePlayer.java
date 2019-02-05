package com.example.android.popularmovies;

/*Example code adapted from:
  YouTube player example - https://www.youtube.com/watch?v=W4hTJybfU7s&t=440s
*/

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class mYouTubePlayer extends YouTubeBaseActivity {

    private static final String TAG = mYouTubePlayer.class.getSimpleName();
    Button buttonStopVideo;
    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    public static final String API_KEY = BuildConfig.YouTubeKey;

    String videoKey = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubeplayer);

        mYouTubePlayerView = findViewById(R.id.youTubePlayer);
        buttonStopVideo = findViewById(R.id.buttonStopVideo);

        buttonStopVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            videoKey = mBundle.get("videoKey").toString();
        } else {
            Toast.makeText(this, "Movie trailer not found. Please try again.", Toast.LENGTH_LONG).show();
            finish();
        }

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "Initialization finished");
                youTubePlayer.loadVideo(videoKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "Initialization failed");
            }
        };

        mYouTubePlayerView.initialize(API_KEY, mOnInitializedListener);

    }

}