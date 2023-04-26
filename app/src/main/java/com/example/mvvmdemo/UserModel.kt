package com.example.mvvmdemo

import java.util.*

/**
 * Model层进行数据处理并回调返回给View进行数据更新
 */
class UserModel {


    companion object{
        const val STATE_LOGIN_LOADING=0
        const val STATE_LOGIN_SUCCESS=1
        const val STATE_LOGIN_FAILED=2
    }



    /**
     * 进行登录操作
     */

    private val api by lazy {
        API()
    }


    private val random:Random= Random()

    fun doLogin( account: String, password: String,block: (Int) -> Unit){

        block(STATE_LOGIN_LOADING)

        //开始去调用登录的API
        //api.login

        //有结果，此操作为耗时操作
        //向服务器提交数据，这个时候，会使用非主线程去操作
        //异步操作，通知UI的时候，要切换主线程，否则报错
        //因为UI只能在主线程更新



        //0..1
        val randomValue = random.nextInt(2)

        if(randomValue==0){
            block(STATE_LOGIN_SUCCESS)
        }else{
            block(STATE_LOGIN_FAILED)
        }
    }

    fun checkUserState(account: String,block:(Int)->Unit){
        //0表示该账号已经注册
        //1表示该账号没有注册
        block(random.nextInt(2))
    }


}