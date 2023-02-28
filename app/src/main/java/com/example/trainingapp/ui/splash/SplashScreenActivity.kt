package com.example.trainingapp.ui.splash

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.pm.PackageInfoCompat
import com.example.trainingapp.MainActivity
import com.example.trainingapp.ui.intro.IntroActivity
import com.example.trainingapp.R
import com.example.trainingapp.services.api.DkiApiSingleton
import com.example.trainingapp.ui.login.LoginActivity
import com.example.trainingapp.utils.AppPreferences
import com.example.trainingapp.utils.Utils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val api = DkiApiSingleton.api()
        val appPreferences = AppPreferences(this)
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.startApi(
                Utils.TOKEN,
                appPreferences.cif,
                android.os.Build.MODEL,
                "",
                "Android",
                "null",
                appPreferences.tokenlogin,
                getVersionName(),
                ""
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    delay(2000)
                    if (response.body()?.statuscode.equals("1")) {
                        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    } else {
                        if(appPreferences.isExist("flagIntro") && appPreferences.flagIntro){
                            startActivity(
                                Intent(this@SplashScreenActivity, LoginActivity::class.java)
                            )
                        }else{
                            startActivity(
                                Intent(this@SplashScreenActivity, IntroActivity::class.java)
                            )
                        }
                    }
                } else {
                    Toast.makeText(
                        this@SplashScreenActivity,
                        "Something went wrong with the server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            finish()
        }

    }

    private fun getVersionName(): String {
        val packageManager = applicationContext.packageManager
        val packageName = packageName

        @Suppress("DEPRECATION")
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        return PackageInfoCompat.getLongVersionCode(packageInfo).toString()
    }
}