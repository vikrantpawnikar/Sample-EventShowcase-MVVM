package com.vikrant.core.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Events")
@Parcelize
data class Events(
    @field:PrimaryKey(autoGenerate = true) var id: Int,
    @SerializedName("events")
    val events: List<EventDetail>?
) : Parcelable