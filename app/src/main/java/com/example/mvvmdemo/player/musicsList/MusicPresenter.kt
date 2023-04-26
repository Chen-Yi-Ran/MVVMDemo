package com.example.mvvmdemo.player.musicsList

import com.example.mvvmdemo.player.DataListenerContainer
import com.example.mvvmdemo.player.domain.Music

class MusicPresenter {

    private val musicModel by lazy {
        MusicModel()
    }
    enum class MusicLoadState{
        LOADING,EMPTY,SUCCESS,ERROR
    }

    val musicList=DataListenerContainer<List<Music>>()
    val loadState=DataListenerContainer<MusicLoadState>()

    private val page=1
    private val size=30

    fun getMusic() {
        loadState.value=MusicLoadState.LOADING

       //从model层中获取音乐列表
        musicModel.loadMusicByPage(page,size,object :MusicModel.OnMusicLoadResult{
            override fun onSuccess(result: List<com.example.mvvmdemo.player.domain.Music>) {

                musicList.value=result
                loadState.value=if(result.isEmpty()){
                    MusicLoadState.EMPTY
                }else{
                    MusicLoadState.SUCCESS
                }
            }

            override fun onError(msg: String, code: Int) {
                 loadState.value=MusicLoadState.ERROR
                println("error...${msg},code${code}")
            }


        })
    }
}