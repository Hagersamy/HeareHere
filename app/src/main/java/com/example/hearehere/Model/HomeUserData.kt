package com.example.hearehere.Model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import android.widget.ProgressBar
import kotlinx.parcelize.RawValue

@Parcelize
data class HomeUserData(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("status")
    val status: String?
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("current_page")
        val current_page: Int?,
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("first_page_url")
        val first_page_url: String?,
        @SerializedName("from")
        val from: Int?,
        @SerializedName("last_page")
        val last_page: Int?,
        @SerializedName("last_page_url")
        val last_page_url: String?,
        @SerializedName("links")
        val links: List<Link?>?,
        @SerializedName("next_page_url")
        val next_page_url: String?,
        @SerializedName("path")
        val path: String?,
        @SerializedName("per_page")
        val per_page: Int?,
        @SerializedName("prev_page_url")
        val prev_page_url: String?,
        @SerializedName("to")
        val to: Int?,
        @SerializedName("total")
        val total: Int?
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("books")
            val books: List<Book?>?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("title")
            val title: String?
        ) : Parcelable {
            @Parcelize
            data class Book(
                @SerializedName("author_name")
                val author_name: String?,
                @SerializedName("progress_bar")
                val progress_bar:Double,
                @SerializedName("id")
                val id: Int?,
                @SerializedName("see_more")
                val see_more: Int?,
                @SerializedName("pivot")
                val pivot: Pivot?,
                @SerializedName("thumbnail_link") //book img
                val thumbnail_link: String?,
                @SerializedName("title")
                val title: String?
            ) : Parcelable {
                @Parcelize
                data class Pivot(
                    @SerializedName("book_id")
                    val book_id: Int?,
                    @SerializedName("collection_id")
                    val collection_id: Int?,
                    @SerializedName("order")
                    val order: Int?
                ) : Parcelable
            }
        }

        @Parcelize
        data class Link(
            @SerializedName("active")
            val active: Boolean?,
            @SerializedName("label")
            val label: String?,
            @SerializedName("url")
            val url: String?
        ) : Parcelable
    }
}