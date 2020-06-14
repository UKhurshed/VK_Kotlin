package com.example.kotlinvksdk.presenter

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kotlinvksdk.R
import com.example.kotlinvksdk.view.LoginView
import javax.inject.Inject

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {

    fun login(isSuccess: Boolean) {
        viewState.startLoading()
        Handler().postDelayed({
            viewState.endLoading()
            if(isSuccess){
                viewState.openFriends()
            }
            else{
                viewState.showError(textResource = R.string.login_error_creadentials)
            }
        }, 500)
    }
}