package com.vikrant.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventDetail(
    @SerializedName("type")
    val type: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("datetime_utc")
    val dateTime: String?,
    @SerializedName("venue")
    val venue: Venue?,
    @SerializedName("performers")
    val performers: List<Performer>?,
    @SerializedName("short_title")
    val shortTitle: String?,
):Parcelable