package com.example.kotlinvksdk.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.kotlinvksdk.R
import com.example.kotlinvksdk.adapters.FriendsAdapter
import com.example.kotlinvksdk.model.FriendModel
import com.example.kotlinvksdk.presenter.FriendsPresenter
import com.example.kotlinvksdk.view.FriendsView
import com.github.rahatarmanahmed.cpv.CircularProgressView

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    private lateinit var mAdapter: FriendsAdapter
    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mTxtNoItems: TextView
    private lateinit var mRvFriends: RecyclerView

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        val mTxtSearch: EditText = findViewById(R.id.txt_friends_search)
        mRvFriends = findViewById(R.id.recycler_friends)
        mTxtNoItems = findViewById(R.id.txt_friends_no_item)
        mCpvWait = findViewById(R.id.cpv_friends)

        friendsPresenter.loadFriends()
        mAdapter = FriendsAdapter()
        mRvFriends.adapter = mAdapter
        mRvFriends.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        mRvFriends.setHasFixedSize(true)

        mTxtSearch.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mAdapter.filter(p0.toString())
            }

        })
    }

    override fun showError(textResource: Int) {
        mTxtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        mRvFriends.visibility = View.GONE
        mTxtNoItems.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        mRvFriends.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE
        mAdapter.setupFriends(friendList = friendsList)
    }

    override fun startLoading() {
        mRvFriends.visibility = View.GONE
        mTxtNoItems.visibility = View.GONE
        mCpvWait.visibility = View.VISIBLE
    }

    override fun endLoading() {
        mCpvWait.visibility = View.GONE

    }
}
