package com.mahmoudashraf.geideaapp.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val `data`: List<User>,
    @SerializedName("support")
    val support: Support
)

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String,
    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    val lastName: String,
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    val avatar: String
)

data class Support(
    @SerializedName("url")
    val url: String,
    @SerializedName("text")
    val text: String
)