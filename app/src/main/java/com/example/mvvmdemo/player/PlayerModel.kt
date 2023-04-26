package com.example.mvvmdemo.player

import com.example.mvvmdemo.player.domain.Music

class PlayerModel {

    fun getMusicById(id: String): Music {
        return Music(
            "卡农 $id",
            "https://",
            "https://www."
        )
    }
}