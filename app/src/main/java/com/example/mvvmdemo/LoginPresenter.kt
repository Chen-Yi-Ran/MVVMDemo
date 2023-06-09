package com.example.mvvmdemo


import android.text.TextUtils
import com.example.mvvmdemo.UserModel.Companion.STATE_LOGIN_FAILED
import com.example.mvvmdemo.UserModel.Companion.STATE_LOGIN_LOADING
import com.example.mvvmdemo.UserModel.Companion.STATE_LOGIN_SUCCESS

/**
 * Presenter层
 */
class LoginPresenter {
    private val userModel by lazy {
        UserModel()
    }

    fun checkUserNameState(account: String, callback: OnCheckUserNameStateResultCallback) {

        userModel.checkUserState(account) {
            when (it) {
                0 -> {
                    callback.onExist()
                }
                1 -> {
                    callback.onNotExist()
                }
            }
        }
    }


    interface OnCheckUserNameStateResultCallback {

        fun onNotExist()

        fun onExist()
    }

    fun doLogin(userName: String, password: String, callback: OnDoLoginStateChange) {
        //检测账号格式是否正确
        if (TextUtils.isEmpty(userName)) {
            //提示账号有问题
            callback.onAccountFormatError()
            return
        }
        //检查密码长度是否正确
        if (TextUtils.isEmpty(password)) {
            //提示密码有问题
            callback.onPasswordEmpty()
            return
        }

        userModel.doLogin(userName, password) {
            when (it) {
                STATE_LOGIN_LOADING -> {
                    callback.onLoading()
                }
                STATE_LOGIN_SUCCESS -> {
                    callback.onLoginSuccess()
                }
                STATE_LOGIN_FAILED -> {
                    callback.onLoginFailed()
                }
            }
        }
    }


    interface OnDoLoginStateChange {


        fun onAccountFormatError()

        fun onPasswordEmpty()

        fun onLoading()

        fun onLoginSuccess()

        fun onLoginFailed()
    }

}