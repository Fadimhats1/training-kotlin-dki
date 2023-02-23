package com.example.trainingapp.model.dki.login

import com.google.gson.annotations.SerializedName

data class DkiLoginResponse(

	@field:SerializedName("statuscode")
	val statuscode: String? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("cif")
	val cif: String? = null,

	@field:SerializedName("tokenlogin")
	val tokenlogin: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("pass")
	val pass: String? = null
)
