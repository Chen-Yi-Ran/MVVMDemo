package com.example.mvvmdemo.player

/**
 * 数据容器
 * 可以监听数据的变化
 */
class DataListenerContainer<T> {

    var value:T?=null
        set(value:T?){
            blocks.forEach {
                //invoke的时候调用block函数,将value值传给block函数去执行
                it.invoke(value)
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