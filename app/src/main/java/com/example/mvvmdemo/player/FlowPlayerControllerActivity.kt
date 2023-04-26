package com.example.mvvmdemo.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_player.*

class FlowPlayerControllerActivity : AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player_controller)
        playerPresenter.registerCallback(this)
        initListener()
    }

    private fun initListener() {

        playerOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }

    override fun onTitleChange(title: String) {

    }

    override fun onProgressChange(current: Int) {

    }

    override fun onPlaying() {
        playerOrPauseBtn.text="暂停"

    }

    override fun onPlayingPause() {
        playerOrPauseBtn.text="播放"
    }

    override fun onCoverChange(cover: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }
}