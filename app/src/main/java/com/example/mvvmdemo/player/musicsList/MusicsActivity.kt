package com.example.mvvmdemo.player.musicsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_musics.*

class MusicsActivity : AppCompatActivity() {

    private val musicPresenter by lazy {
        MusicPresenter()
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

    override fun onStart() {
        super.onStart()
        musicPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        musicPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        musicPresenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPresenter.onDestroy()
    }

    override fun onStop() {
        super.onStop()
        musicPresenter.onStop()
    }
}