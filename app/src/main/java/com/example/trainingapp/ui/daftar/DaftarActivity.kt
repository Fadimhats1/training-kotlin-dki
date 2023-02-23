package com.example.trainingapp.ui.daftar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.trainingapp.R
import com.example.trainingapp.model.User
import com.example.trainingapp.services.DatabaseHelper
import com.example.trainingapp.ui.login.LoginActivity
import com.example.trainingapp.ui.syarat.SyaratActivity

import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_daftar.*
import kotlinx.android.synthetic.main.activity_syarat.*

class DaftarActivity : AppCompatActivity() {
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            checkbox_kebijakan.isChecked = result.data!!.getBooleanExtra("setuju", true)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        supportActionBar?.hide()
        val db = DatabaseHelper(this)

        daftar_submit.setOnClickListener {
            if (validate()) {
                if (db.addUser(
                        User(
                            name = nama.text.toString(),
                            email = email.text.toString(),
                            password = password.text.toString(),
                            phoneNumber = phone.text.toString()
                        )
                    )
                ) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish();
                }
            }
        }
        to_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java));
        }
        to_syarat_page.setOnClickListener {
            resultLauncher.launch(Intent(this, SyaratActivity::class.java))
        }
    }

    private fun validate(): Boolean {
        val regexPhone = "^08[0-9]{8,11}\$".toRegex()
        val regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex()
        val regexText = "^[a-zA-Z]{4,}(?:\\s[a-zA-Z]+)*\$".toRegex()
        val regexPassword = "^[a-zA-Z0-9!@#\\$%\\^\\&*)\\\\(+=._-]{8,}\$".toRegex()

        if (!regexText.matches(nama.text.toString())) {
            nama!!.error = "Input tidak boleh mengandung simbol, angka dan harus lebih dari 4 karakter"
            return false
        }
        if (!regexEmail.matches(email.text.toString())) {
            email!!.error = "Input tidak boleh kosong atau format email salah"
            return false
        }
        if (!regexPassword.matches(password.text.toString())) {
            password!!.error = "Input tidak boleh kosong atau kurang dari 8 karakter"
            return false
        }
        if (!regexPhone.matches(phone.text.toString())) {
            phone!!.error = "Input tidak boleh kosong atau kurang dari 10 karakter"
            return false
        }
        return true;
    }
}

