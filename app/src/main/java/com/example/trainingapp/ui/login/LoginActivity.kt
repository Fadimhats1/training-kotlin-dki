package com.example.trainingapp.ui.login

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.trainingapp.MainActivity
import com.example.trainingapp.R
import com.example.trainingapp.model.AnimesResponse
import com.example.trainingapp.model.dki.login.DkiLoginResponse
import com.example.trainingapp.model.dki.login.Login
import com.example.trainingapp.services.DatabaseHelper
import com.example.trainingapp.services.api.DkiApiSingleton
import com.example.trainingapp.services.api.IDkiApiService
import com.example.trainingapp.services.api.IMalApiService
import com.example.trainingapp.services.api.MalApiSingleton
import com.example.trainingapp.ui.daftar.DaftarActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Base64.getEncoder
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class LoginActivity : AppCompatActivity() {
    val IMEI: String? = null
    val token ="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5UZG1aak00WkRrM05qWTBZemM1TW1abU9EZ3dNVEUzTVdZd05ERTVNV1JsWkRnNE56YzRaQT09In0.eyJhdWQiOiJodHRwOlwvXC9vcmcud3NvMi5hcGltZ3RcL2dhdGV3YXkiLCJzdWIiOiJlcmljay5waW5lbmthQGNhcmJvbi5zdXBlciIsImFwcGxpY2F0aW9uIjp7Im93bmVyIjoiZXJpY2sucGluZW5rYSIsInRpZXJRdW90YVR5cGUiOiJyZXF1ZXN0Q291bnQiLCJ0aWVyIjoiVW5saW1pdGVkIiwibmFtZSI6Im1vYmlsZSIsImlkIjo0LCJ1dWlkIjpudWxsfSwic2NvcGUiOiJhbV9hcHBsaWNhdGlvbl9zY29wZSBkZWZhdWx0IiwiaXNzIjoiaHR0cHM6XC9cL2FwaW0tZGV2LmRhbmFraW5pLmNvLmlkOjQ0M1wvb2F1dGgyXC90b2tlbiIsInRpZXJJbmZvIjp7IlVubGltaXRlZCI6eyJ0aWVyUXVvdGFUeXBlIjoicmVxdWVzdENvdW50Iiwic3RvcE9uUXVvdGFSZWFjaCI6dHJ1ZSwic3Bpa2VBcnJlc3RMaW1pdCI6MCwic3Bpa2VBcnJlc3RVbml0IjpudWxsfX0sImtleXR5cGUiOiJQUk9EVUNUSU9OIiwic3Vic2NyaWJlZEFQSXMiOlt7InN1YnNjcmliZXJUZW5hbnREb21haW4iOiJjYXJib24uc3VwZXIiLCJuYW1lIjoibW9iaWxlIiwiY29udGV4dCI6IlwvbW9iaWxlXC92MSIsInB1Ymxpc2hlciI6ImVyaWNrLnBpbmVua2EiLCJ2ZXJzaW9uIjoidjEiLCJzdWJzY3JpcHRpb25UaWVyIjoiVW5saW1pdGVkIn1dLCJjb25zdW1lcktleSI6IkNsVkN6Vk9vX3UxZVlkWjFjWVdaZnNjRkF4c2EiLCJleHAiOjM3MzQyNTk3NjksImlhdCI6MTU4Njc3NjEyMiwianRpIjoiNDE3NGVhZDAtMDg0Zi00NjY5LTk0OTUtZGYxZjRlMWY5YTgzIn0.i-Wg8qlQ7yRkupFk2v2kNwx-iKk2dJYkL50tm0jskViP1nx-VgvDD5PPRqx0_KhSTXbke7AwrXPd4KsvRtQlQNSzpRXnIR96AdAnu6SY9jfDIqw6aavhhqpny-YdbcSBTQ6iU_Geq3x1W1fnM5pNLljcjqz33cXXtxjMcwqJTEILmH4FQ63JRzY3Cuo0jIkG-AXVQT7PWGKV6xNnPdHyTAZvBYtG5EhOQyO7XwQR8DaQzBgsBCJ1FqYhUJgwD4lGlh8-kIAKebnzmKHaXL5ewtm5h5R5dHC_bGO1aCU1QrhvDuFr7_UyaXG25AZlCn7bkw0ZYMUaEhAeMMW15NdyBw"
    @SuppressLint("NewApi")
    lateinit var encorder: Base64.Encoder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encorder = getEncoder()
        }
        val encryptMsg =
            encrypt(password.text.toString(), "Dqaz@wsxnedv@rfb1tgb<yhn1yhguyn1", "4528711254935489")
        supportActionBar?.hide()
        val db = DatabaseHelper(this)
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        login.setOnClickListener {
            coroutineScope.launch {
                try {
                    DkiApiSingleton.resCall().login(token, email.text.toString(), password.text.toString(), IMEI.toString()).enqueue(object : Callback<DkiLoginResponse>{
                        override fun onResponse(
                            call: Call<DkiLoginResponse>,
                            response: Response<DkiLoginResponse>
                        ) {
                            val res = response.body()?.statuscode
                            Log.e(TAG, "onResponse: " + res.toString())
                        }

                        override fun onFailure(call: Call<DkiLoginResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
                } catch (e: Exception) {
                }
            }
        }
        create_account.setOnClickListener{
            val intent = Intent(this, DaftarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun cipher(opmode: Int, secretKey: String, secIV: String): Cipher {
        if (secretKey.length != 32) throw RuntimeException("SecretKey length is not 32 chars")
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secIV.toByteArray(Charsets.UTF_8))
        c.init(opmode, sk, iv)
        return c
    }

    @SuppressLint("NewApi")
    fun encrypt(str: String, secretKey: String, secIV: String): String {
        val encrypted =
            cipher(Cipher.ENCRYPT_MODE, secretKey, secIV).doFinal(str.toByteArray(Charsets.UTF_8))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return String(encorder.encode(encrypted))
        } else {
            return String(android.util.Base64.encode(encrypted, android.util.Base64.NO_WRAP))
        }
//        return cipher(Cipher.ENCRYPT_MODE, secretKey,secIV).toString()
    }
}
