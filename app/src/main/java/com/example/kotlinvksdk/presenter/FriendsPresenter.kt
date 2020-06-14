package com.example.kotlinvksdk.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kotlinvksdk.R
import com.example.kotlinvksdk.model.FriendModel
import com.example.kotlinvksdk.providers.FriendsProvider
import com.example.kotlinvksdk.view.FriendsView

@InjectViewState
class FriendsPresenter:MvpPresenter<FriendsView>() {

    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).testLoadFriends(hasFriends = true)
    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>){
        if (friendsList.size==0){
            viewState.setupEmptyList()
            viewState.showError(R.string.friends_no_item)
        } else{
            viewState.setupFriendsList(friendsList = friendsList)
        }

    }
}