package com.example.trainingapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.trainingapp.MainActivity
import com.example.trainingapp.R
import com.example.trainingapp.services.DatabaseHelper
import com.example.trainingapp.ui.daftar.DaftarActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        val db = DatabaseHelper(this)

        login.setOnClickListener {
            if(!db.checkLogin(email.text.toString(), password.text.toString())){
                login_error.visibility = View.VISIBLE
            }else{
                startActivity(Intent(this, MainActivity::class.java))
                finish();
            }
        }
        create_account.setOnClickListener{
            val intent = Intent(this, DaftarActivity::class.java)
            startActivity(intent)
        }
    }
}