package com.example.hearehere.Model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class RegisterData(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("status")
    val status: String?
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("updated_at")
        val updatedAt: String?
    ) : Parcelable
}