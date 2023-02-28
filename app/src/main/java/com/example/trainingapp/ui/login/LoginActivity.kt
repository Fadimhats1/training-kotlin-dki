package com.example.trainingapp.ui.login

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.pm.PackageInfoCompat
import com.example.trainingapp.MainActivity
import com.example.trainingapp.R
import com.example.trainingapp.model.dki.login.DataStart
import com.example.trainingapp.services.api.DkiApiSingleton
import com.example.trainingapp.ui.daftar.DaftarActivity
import com.example.trainingapp.utils.AppPreferences
import com.example.trainingapp.utils.Utils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import java.util.*
import java.util.Base64.getEncoder
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class LoginActivity : AppCompatActivity() {


    @SuppressLint("NewApi")

    private val arraySecretKey = arrayOf("Dqaz@wsxnedv@rfb1tgb<yhn1yhguyn1", "4528711254935489")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       supportActionBar?.hide()
        val token = Utils.TOKEN
        val api = DkiApiSingleton.api()
        val appPreferences = AppPreferences(this)
        login.setOnClickListener {
            login.isEnabled = false
            val encryptPass =
                Utils.encrypt(password.text.toString(), arraySecretKey[0], arraySecretKey[1])
            Log.e(TAG, "onCreate: ${encryptPass}", )

            CoroutineScope(Dispatchers.IO).launch {
                val response = api.login(token, email.text.toString(), encryptPass)

                if (response.isSuccessful) {
                    val data = response.body()?.data
                    appPreferences.cif = data?.cif
                    appPreferences.tokenlogin = data?.tokenlogin
                    val _response = api.startApi(
                        token,
                        appPreferences.cif!!,
                        android.os.Build.MODEL,
                        "",
                        "Android",
                        "null",
                        appPreferences.tokenlogin!!,
                        Utils.getVersionName(applicationContext.packageManager, packageName),
                        ""
                    )
                    withContext(Dispatchers.Main) {
                        if (_response.isSuccessful) {
                            if (_response.body()?.statuscode.equals("1")) {
                                dataStartToAppPref(_response.body()?.data)
                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        MainActivity::class.java
                                    )
                                )
                                finish()
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Something went wrong with the server",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            login.isEnabled = true
        }
        create_account.setOnClickListener {
            val intent = Intent(this, DaftarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dataStartToAppPref(data: DataStart?) {
        val appPreferences = AppPreferences(this)
        appPreferences.nama = data?.nama
        appPreferences.customerType = data?.customerType
        appPreferences.tkdn = data?.tKDN
        appPreferences.apiKey = data?.apikey
        appPreferences.versionStartRes = data?.version
    }
}
