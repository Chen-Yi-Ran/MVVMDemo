package com.example.mvvmdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmdemo.lifecycle.ILifecycle
import com.example.mvvmdemo.lifecycle.LifeState
import com.example.mvvmdemo.lifecycle.LifecycleProvider

open class BaseActivity:AppCompatActivity() {


     val lifeProvider by lazy {
         LifecycleProvider()
     }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeProvider.makeLifeState(LifeState.CREATE)
    }


    override fun onStart() {
        super.onStart()
        lifeProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
        lifeProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifeProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeProvider.makeLifeState(LifeState.DESTROY)
    }

    override fun onStop() {
        super.onStop()
        lifeProvider.makeLifeState(LifeState.STOP)
    }
}