package com.example.mvvmdemo.player



interface IPlayerCallback {

    fun onTitleChange(title:String)

    fun onProgressChange(current:Int)

    fun onPlaying()
    fun onPlayingPause()
    fun onCoverChange(cover:String)
}