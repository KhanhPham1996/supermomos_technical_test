package com.example.userlist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
/**
 * Created by BMHenry at 9/8/2021
 */

data class UserDetail(
    @SerializedName("avatar_url") var avatarUrl : String,
    @SerializedName("followers") var followers : Int,
    @SerializedName("following") var following : Int,
    @SerializedName("bio") var bio : String? = "Bio",
    @SerializedName("name") var name : String,
    @SerializedName("location") var location : String,
    @SerializedName("public_repos") var publicRepos: Int
)

