package com.example.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserModel.OnDoLoginStateChange {

    private val userModel by lazy{
        UserModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //设置点击事件
        initListener()
    }

    private fun initListener() {

        LoginBtn.setOnClickListener {
            //去进行登录
            toLogin()
        }


    }

    /**
     * 处理登录逻辑
     */
    private fun toLogin() {
        //做登录的逻辑处理
        val account:String =accountInputBox.text.toString()
        val password:String=passwordInputBox.text.toString()
        //检测账号格式是否正确
        if(TextUtils.isEmpty(account)){
            //提示账号有问题
            return
        }
        //检查密码长度是否正确
        if(TextUtils.isEmpty(password)){
            //提示密码有问题
            return
        }
        //给密码加密
        userModel.checkUserState(account){
            when(it){
                0->{
                    //可用，已经注册了
                    //进行登录,此操作为异步
                    userModel.doLogin(this,account,password)

                    //禁止按钮可以点击,防止重复提交
                    LoginBtn.isEnabled=false

                }
                1->{
                    //表示没有注册，不可用
                    //给出提示
                }
            }
        }


    }

    override fun onLoading() {
        loginTipsText.text="登录中..."

    }

    override fun onLoginSuccess() {
        loginTipsText.text="登录成功..."
    }

    override fun onLoginFailed() {
        loginTipsText.text="登录失败..."
    }
}