package com.example.diabetter.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuData(
    var calories : Double
) : Parcelable
