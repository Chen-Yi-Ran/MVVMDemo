package com.example.mvvmdemo.player.musicsList

import android.os.Bundle
import com.example.mvvmdemo.R
import com.example.mvvmdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_musics.*

class MusicsActivity : BaseActivity() {

    private val musicPresenter by lazy {
        MusicPresenter()
    }

    init {
        addLifeListener(musicPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musics)
        initDataListener()
        initViewListener()


    }

    /**
     * 监听数据变化
     */
    private fun initDataListener() {
        musicPresenter.musicList.addListener {
            println(Thread.currentThread().name)
             //数据变化
            println("加载状态--->${it?.size}")

            musicCountText?.text="加载到了 --- >${it?.size}条数据"
        }

        musicPresenter.loadState.addListener {
             println("加载状态--->${it}")
        }
    }

    private fun initViewListener() {
        getMusicsBtn.setOnClickListener {
            musicPresenter.getMusic()
        }
    }


}