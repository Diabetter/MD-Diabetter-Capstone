package com.example.diabetter.data.remote.response

data class AllHistoryResponse(
	val docs: List<DocsItem>,
	val status: String
)

data class DocsItem(
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
)

