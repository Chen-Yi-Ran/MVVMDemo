package com.example.mvvmdemo.player

import com.example.mvvmdemo.lifecycle.ILifecycle
import com.example.mvvmdemo.player.domain.Music


/**
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 *
 * ---------------
 * 播放的状态
 * - 通知UI改变成播放状态
 * - 通知UI进度的变化
 * - 上一首，下一首
 * - 通知UI歌曲标题变化
 * - 通知UI歌曲封面变化
 * - 暂停音乐
 * - 更新UI状态为暂停
 */
//做成单例类，保存同一个对象，使用Presenter数据一样，私有化构造
class PlayerPresenter private constructor() :ILifecycle{

     var currentMusic = DataListenerContainer<Music>()

    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING
    }

    companion object {
        val instance by lazy {
            PlayerPresenter()
        }
    }

    private val playerModel by lazy {
        PlayerModel()
    }

    private val player by lazy {
        MusicPlayer()
    }


    var currentPlayState = DataListenerContainer<PlayState>()


    //val callbacksList = arrayListOf<IPlayerCallback>()

//    fun registerCallback(callback: IPlayerCallback) {
//
//        if (!callbacksList.contains(callback)) {
//            callbacksList.add(callback)
//        }
//    }
//
//    fun unRegisterCallback(callback: IPlayerCallback) {
//        callbacksList.remove(callback)
//    }

    /**
     * 根据状态控制音乐播放还是暂停
     */
    fun doPlayOrPause() {
        if (currentMusic.value == null) {
            //去获取一首歌曲
            currentMusic.value = playerModel.getMusicById("卡农")
        }
        player.play(currentMusic.value)

     //   dispatchTitleChange("当前播放的歌曲标题")
      //  dispatchCoverChange("当前播放的歌曲封面")

        if (currentPlayState.value != PlayState.PLAYING) {
            //开始播放音乐
            //TODO:
         //   dispatchPlayingState()
            currentPlayState.value = PlayState.PLAYING
        } else {
            currentPlayState.value = PlayState.PAUSE
            //暂停播放
         //   dispatchPlayerPauseState()
        }

    }


//    private fun dispatchPlayerPauseState() {
//        callbacksList.forEach {
//            it.onPlayingPause()
//        }
//
//    }
//
//    private fun dispatchPlayingState() {
//
//        callbacksList.forEach {
//            //遍历出callbacksList中的元素执行onPlaying
//            it.onPlaying()
//        }
//
//    }


    /**
     * 播放下一首
     */
    fun playNext() {
        currentMusic.value=playerModel.getMusicById("下一首：梦中的婚礼")
        //1.拿到下一首歌曲 --> 变更UI，包括标题和封面
     //   dispatchTitleChange("切换到下一首，标题变化了...")
      //  dispatchCoverChange("切换到下一首，封面变化了...")
        //2.设置给播放器


        //3.等待播放的回调通知
        currentPlayState.value = PlayState.PLAYING
    }
//
//    private fun dispatchCoverChange(cover: String) {
//        callbacksList.forEach {
//            it.onCoverChange(cover)
//        }
//    }
//
//    private fun dispatchTitleChange(title: String) {
//        callbacksList.forEach {
//            it.onTitleChange(title)
//        }
//    }

    /**
     * 播放上一首
     */
    fun playPre() {
        currentMusic.value=playerModel.getMusicById("上一首: 虫儿飞")
      //  dispatchTitleChange("切换到上一首，标题变化了...")
      //  dispatchCoverChange("切换到上一首，封面变化了...")
        currentPlayState.value = PlayState.PLAYING
    }
    override fun onCreate() {




    }

    override fun onStart() {
        println("监听网络状态变化")

        //开始监听网络变化
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {
        println("停止网络状态变化监听")

        //停止网络状态信息变化更新

    }

    override fun onDestroy() {

    }
}