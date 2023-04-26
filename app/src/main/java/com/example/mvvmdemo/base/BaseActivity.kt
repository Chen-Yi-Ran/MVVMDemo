package com.example.mvvmdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmdemo.lifecycle.ILifecycle

open class BaseActivity:AppCompatActivity() {

    private val lifecycleListener= arrayListOf<ILifecycle>()

    fun addLifeListener(listener:ILifecycle){
        if(!lifecycleListener.contains(listener)){
            lifecycleListener.add(listener)
        }
    }

    fun removeLifeListener(listener: ILifecycle){
        lifecycleListener.remove(listener)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleListener.forEach {
            it.onCreate()
        }
    }


    override fun onStart() {
        super.onStart()
        lifecycleListener.forEach {
            it.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleListener.forEach {
            it.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        lifecycleListener.forEach {
            it.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleListener.forEach {
            it.onDestroy()
        }
    }

    override fun onStop() {
        super.onStop()
        lifecycleListener.forEach {
            it.onStop()
        }
    }
}