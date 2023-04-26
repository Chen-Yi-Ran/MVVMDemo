package com.example.mvvmdemo.player.musicsList

import com.example.mvvmdemo.player.domain.Music

class MusicModel {
    fun loadMusicByPage(page: Int, size: Int,callback:OnMusicLoadResult) {

        val result= arrayListOf<Music>()

        Thread{
            for(i  in (0 until size)){
                result.add(
                    Music(
                        "音乐的名称$i",
                        "cover 封面$i",
                        "url---> $i"
                    )
                )
            }
            //数据请求完成
                //通知数据更新
            callback.onSuccess(result)
        }.start()

    }


    interface OnMusicLoadResult{

        fun onSuccess(result:List<Music>)

        fun onError(msg:String,code:Int)
    }
}