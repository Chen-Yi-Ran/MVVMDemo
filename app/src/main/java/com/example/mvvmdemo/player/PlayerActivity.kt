package com.example.mvvmdemo.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmdemo.R
import com.example.mvvmdemo.base.BaseActivity
import com.example.mvvmdemo.lifecycle.LifecycleProvider
import com.example.mvvmdemo.player.musicsList.MusicPresenter
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : BaseActivity(){

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }
    private val musicPresenter by lazy {
        MusicPresenter()
    }
    init {
       lifeProvider.addLifeListener(musicPresenter)
        lifeProvider.addLifeListener(playerPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

 //       playerPresenter.registerCallback(this)

       initListener()

        initDataListener()
    }

    /**
     * 对数据进行监听
     */
    private fun initDataListener() {
        playerPresenter.currentMusic.addListener {
            //音乐内容发生变化，更新UI
            songTitle.text = it?.name
            println("封面改变了...${it?.cover}")


        }
        playerPresenter.currentPlayState.addListener {
            when (it) {
                PlayerPresenter.PlayState.PAUSE -> {
                    playerOrPauseBtn.text = "播放"
                }
                PlayerPresenter.PlayState.PLAYING -> {
                    playerOrPauseBtn.text = "暂停"
                }
            }
        }

    }

    /**
     * 给控件设置相关事件
     */
    private fun initListener() {
        playerOrPauseBtn.setOnClickListener {
            //调用Presenter层的播放或者暂停方法
            playerPresenter.doPlayOrPause()
        }
        playerNextBtn.setOnClickListener {
            playerPresenter.playNext()
        }
        playPrevioustBtn.setOnClickListener {
            playerPresenter.playPre()
        }

    }


//    override fun onDestroy() {
//        super.onDestroy()
//        playerPresenter.unRegisterCallback(this)
//    }

//    override fun onTitleChange(title: String) {
//        songTitle?.text = title
//
//    }
//
//    override fun onProgressChange(current: Int) {
//
//    }
//
//    override fun onPlaying() {
//        //播放中--->显示暂停
//        playerOrPauseBtn.text = "暂停"
//
//    }
//
//    override fun onPlayingPause() {
//        //暂停--->显示播放
//        playerOrPauseBtn.text = "播放"
//    }
//
//    override fun onCoverChange(cover: String) {
//
//        print("封面改变了...$cover")
//
//    }
}