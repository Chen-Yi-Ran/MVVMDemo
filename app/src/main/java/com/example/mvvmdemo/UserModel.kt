package com.example.mvvmdemo

import java.util.*

class UserModel {

    /**
     * 进行登录操作
     */

    private val api by lazy {
        API()
    }

    private val random:Random= Random()

    fun doLogin(callback: OnDoLoginStateChange, account: String, password: String){

        callback.onLoading()

        //开始去调用登录的API
        //api.login

        //有结果，此操作为耗时操作
        //0..1
        val randomValue = random.nextInt(2)

        if(randomValue==0){
            callback.onLoginSuccess()
        }else{
            callback.onLoginFailed()
        }
    }

    fun checkUserState(account: String,block:(Int)->Unit){
        //0表示该账号已经注册
        //1表示该账号没有注册
        block(random.nextInt(2))
    }

    interface OnDoLoginStateChange{
        fun onLoading()

        fun onLoginSuccess()

        fun onLoginFailed()
    }

}