package com.vikrant.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Venue(
    @SerializedName("state")
    val state: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("postal_code")
    val postalCode: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("extended_address")
    val extendedAddress: String?,
    @SerializedName("id")
    val id: Int
):Parcelable
