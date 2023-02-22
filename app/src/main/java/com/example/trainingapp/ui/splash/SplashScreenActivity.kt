package com.example.trainingapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.trainingapp.ui.intro.IntroActivity
import com.example.trainingapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handler = Handler(Looper.getMainLooper())
//        var wrapper = findViewById<ConstraintLayout>(R.id.splash_wrapper);
//
//        wrapper.alpha = 0f

//        wrapper.animate().setDuration(2000).alpha(1f).withEndAction {
//            startActivity(Intent(this, IntroActivity::class.java))
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//            finish();
//        }


        handler.postDelayed({
            startActivity(Intent(this, IntroActivity::class.java));
            finish();
        }, 2000)

    }
}