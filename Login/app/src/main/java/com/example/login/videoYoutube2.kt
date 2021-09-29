package com.example.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.login.ui.clases.PeliculaFireBase
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class videoYoutube2 : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    var VIDEO_ID = ""
    val YOUTUBE_API_KEY = "AIzaSyDRzPGhQTqsJnMgAT2hxi1Cvnvj5853wiA"
    override fun onCreate(savedInstanceState: Bundle?) {
        VIDEO_ID = intent.getSerializableExtra("youTubeID") as String
        Log.i("2youtube","${VIDEO_ID}")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_youtube2)

        val youTubeView = findViewById(R.id.youtube_view) as YouTubePlayerView

        youTubeView.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if (!p2) {

            //p1?.cueVideo(VIDEO_ID)
            //p1?.loadVideo(VIDEO_ID)
            p1?.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE)
            p1?.loadVideo(VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {

    }


}