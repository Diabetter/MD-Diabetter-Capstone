package com.example.diabetter.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MakananResponse(
	val data: DataMakanan,
	val url: String,
	val status: String
) : Parcelable

@Parcelize
data class DataMakanan(
	val kalori: String,
	val no: String,
	val karbohidrat: String,
	val nama: String,
	val protein: String,
	val jenis: String,
	val rating: String,
	val lemak: String
) : Parcelable
