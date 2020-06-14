package com.example.kotlinvksdk.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinvksdk.R
import com.example.kotlinvksdk.model.FriendModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class FriendsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var mFriendsList: ArrayList<FriendModel> = ArrayList()
    private var mSourceList: ArrayList<FriendModel> =  ArrayList()

    fun setupFriends(friendList: ArrayList<FriendModel>){
        mSourceList.clear()
        mSourceList.addAll(friendList)
        filter(query = "")
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if(it.name.contains(query, ignoreCase = false) || it.surname.contains(query, ignoreCase = false)){
                mFriendsList.add(it)
            } else{
                it.city?.let { city ->
                    if(city.contains(query, ignoreCase = false)) {
                        mFriendsList.add(it)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = LayoutInflater.inflate(R.layout.cell_friend,parent, false)
        return FriendsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return mFriendsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is FriendsViewHolder){
            holder.bind(friendModel = mFriendsList[position])
        }
    }



    class FriendsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var mCivAvatar: CircleImageView =  itemView.findViewById(R.id.friends_civ_avatar)
        private var mTxtUsername: TextView = itemView.findViewById(R.id.friends_txt_username)
        private var mTxtCity: TextView = itemView.findViewById(R.id.friends_txt_city)
        private var mImgOnline: View = itemView.findViewById(R.id.friends_img_online)

        @SuppressLint("SetTextI18n")
        fun bind(friendModel: FriendModel){

            friendModel.avatar?.let{url ->
                Picasso.get().load(url).into(mCivAvatar)
            }


            mTxtUsername.text = "${friendModel.name} ${friendModel.surname}"
            mTxtCity.text = itemView.context.getString(R.string.friend_no_city)
            friendModel.city?.let{city -> mTxtCity.text = city}

            if (friendModel.isOnline){
                mImgOnline.visibility = View.VISIBLE
            } else{
                mImgOnline.visibility = View.GONE
            }


        }
    }
}