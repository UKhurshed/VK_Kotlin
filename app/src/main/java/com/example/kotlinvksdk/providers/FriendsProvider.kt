package com.example.kotlinvksdk.providers

import android.os.Handler
import com.example.kotlinvksdk.model.FriendModel
import com.example.kotlinvksdk.presenter.FriendsPresenter

class FriendsProvider(var presenter: FriendsPresenter) {

    fun testLoadFriends(hasFriends: Boolean){
        Handler().postDelayed({
            var friendsList: ArrayList<FriendModel> = ArrayList()
            if(hasFriends){
                var friend1 = FriendModel(name = "Ivan", surname = "Petrov", city = "Moscow", avatar = "https://histrf.ru/uploads/media/person/0001/04/thumb_3560_person_full.jpeg", isOnline = false)
                var friend2 = FriendModel(name = "Khurshed", surname = "Umarov", city = "Khudjand", avatar = "https://sun1-18.userapi.com/c854220/v854220916/1eace7/aaBX1NTt140.jpg", isOnline = true)
                friendsList.add(friend1)
                friendsList.add(friend2)

            }
            presenter.friendsLoaded(friendsList = friendsList)
        },2000)

    }
}