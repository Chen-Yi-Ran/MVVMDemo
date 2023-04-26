package com.example.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), LoginPresenter.OnDoLoginStateChange,
    LoginPresenter.OnCheckUserNameStateResultCallback {

    private val loginPresenter by lazy {
        LoginPresenter()
    }

    private var isUserNameCanBeUse=false

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
            //监听内容变化
        accountInputBox.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                //检查当前账号是否有注册
                loginPresenter.checkUserNameState(p0.toString(),this@LoginActivity)

            }
        })
    }

    /**
     * 处理登录逻辑
     */
    private fun toLogin() {
        //做登录的逻辑处理
        val account:String =accountInputBox.text.toString()
        val password:String=passwordInputBox.text.toString()
        //给密码加密
        if(!isUserNameCanBeUse){
            //提示用户说当前用户名已经被注册了
            return
        }
        //异步操作
        loginPresenter.doLogin(account,password,this)
    }

    override fun onAccountFormatError() {
        loginTipsText?.text="账号不可为空..."
    }

    override fun onPasswordEmpty() {
        loginTipsText?.text="密码不可为空..."
    }

    override fun onLoading() {
        loginTipsText?.text="登录中..."

    }

    override fun onLoginSuccess() {
        //可能登录时候你退出去，loginTipsText销毁为null，会报空指针，所以判空
        loginTipsText?.text="登录成功..."
    }

    override fun onLoginFailed() {
        loginTipsText?.text="登录失败..."
    }

    override fun onNotExist() {
        //用户名不可用
        loginTipsText?.text="该用户名已经注册了"
        isUserNameCanBeUse=false
    }

    override fun onExist() {
        //用户名可用
        loginTipsText?.text="该用户名可以使用"
        isUserNameCanBeUse=true
    }
}