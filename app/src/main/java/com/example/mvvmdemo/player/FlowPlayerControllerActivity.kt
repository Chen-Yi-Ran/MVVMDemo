package com.example.mvvmdemo.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_player.*

class FlowPlayerControllerActivity : AppCompatActivity() {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player_controller)
      //  playerPresenter.registerCallback(this)
        initListener()

        initDataListener()
    }

    private fun initDataListener() {
        playerPresenter.currentPlayState.addListener {
            playerOrPauseBtn.text=if(it==PlayerPresenter.PlayState.PLAYING){
                "暂停"
            }else{
                "播放"
            }
        }
    }

    private fun initListener() {

        playerOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }


}