package com.example.diabetter.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PredictResponse(
	val data: @RawValue Data,
	val status: String
) : Parcelable

@Parcelize
data class Data(
	val date: String,
	val kalori: Double,
	val uid: String,
	val food1: String,
	val karbohidrat: Double,
	val food3: String,
	val food2: String,
	val protein: Double,
	val rating: Double,
	val id: String,
	val lemak: Double
) : Parcelable

