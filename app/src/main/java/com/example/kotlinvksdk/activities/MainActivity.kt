package com.example.kotlinvksdk.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.kotlinvksdk.R
import com.example.kotlinvksdk.presenter.LoginPresenter
import com.example.kotlinvksdk.view.LoginView
import com.github.rahatarmanahmed.cpv.CircularProgressView

class MainActivity : MvpAppCompatActivity(), LoginView {


    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mTxtHello: TextView
    private lateinit var mBtnEnter: Button

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTxtHello = findViewById(R.id.txt_hello)
        mBtnEnter = findViewById(R.id.click_login)
        mCpvWait = findViewById(R.id.cpv_login)

        mBtnEnter.setOnClickListener {
            loginPresenter.login(true)
        }
    }

    override fun startLoading() {
        mBtnEnter.visibility = View.GONE
        mCpvWait.visibility = View.VISIBLE
    }

    override fun openFriends() {
        startActivity(Intent(applicationContext, FriendsActivity::class.java))
    }

    override fun endLoading() {
        mBtnEnter.visibility = View.VISIBLE
        mCpvWait.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, textResource, Toast.LENGTH_SHORT).show()
    }

}

