package com.example.mvvmdemo.player

import android.os.Looper

/**
 * 数据容器
 * 可以监听数据的变化
 */
class DataListenerContainer<T> {

    var value:T?=null
        set(value:T?){

            if(Looper.getMainLooper().thread === Thread.currentThread()){
                //如果是主线程

                blocks.forEach {

                    //判断当前线程是否是主线程
                    //如果是，则直接执行，否则切换到主线程再执行

                    //invoke的时候调用block函数,将value值传给block函数去执行
                    it.invoke(value)
                }
            }else{
                //handler.post()方法切换到主线程
                   App.handler.post(object :Runnable{
                       override fun run() {

                           blocks.forEach {

                               //判断当前线程是否是主线程
                               //如果是，则直接执行，否则切换到主线程再执行

                               //invoke的时候调用block函数,将value值传给block函数去执行
                               it.invoke(value)
                           }
                       }

                   })
            }

        }


    private val blocks= arrayListOf<(T?)->Unit>()
    //当数据变化时，就通知更新


    fun addListener(block:(T?)->Unit){

        if(!blocks.contains(block)){
            blocks.add(block)
        }

    }
}