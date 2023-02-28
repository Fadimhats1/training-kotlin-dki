package com.example.trainingapp.model.dki.login

import com.google.gson.annotations.SerializedName

data class DkiStartResponse(

	@field:SerializedName("statuscode")
	val statuscode: String? = null,

	@field:SerializedName("data")
	val data: DataStart? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataStart(

	@field:SerializedName("Nama")
	val nama: String? = null,

	@field:SerializedName("apikey")
	val apikey: String? = null,

	@field:SerializedName("TKDN")
	val tKDN: String? = null,

	@field:SerializedName("CustomerType")
	val customerType: String? = null,

	@field:SerializedName("version")
	val version: String? = null
)
