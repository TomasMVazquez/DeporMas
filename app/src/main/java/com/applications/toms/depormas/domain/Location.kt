package com.applications.toms.depormas.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    val id: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val city: String
): Parcelable