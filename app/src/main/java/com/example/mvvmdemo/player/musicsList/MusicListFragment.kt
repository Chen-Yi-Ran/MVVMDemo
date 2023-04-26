package com.example.mvvmdemo.player.musicsList

import com.example.mvvmdemo.base.BaseFragment

class MusicListFragment:BaseFragment() {

    private val musicPresenter by lazy {
        MusicPresenter()
    }

    init {
        lifeProvider.addLifeListener(musicPresenter)
    }
}