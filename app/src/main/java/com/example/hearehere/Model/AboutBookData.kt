package com.example.hearehere.Model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class AboutBookData(
    @SerializedName("data")
    val `data`: Data?
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("author_name")
        val authorName: String?,
        @SerializedName("categories")
        val categories: List<Category?>?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("file_link")
        val fileLink: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("thumbnail_link")
        val thumbnail_Link: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("updated_at")
        val updatedAt: String?
    ) : Parcelable {
        @Parcelize
        data class Category(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("title")
            val title: String?
        ) : Parcelable
    }
}