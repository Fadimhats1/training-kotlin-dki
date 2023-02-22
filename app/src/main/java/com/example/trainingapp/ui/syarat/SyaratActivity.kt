package com.example.trainingapp.ui.syarat

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import com.example.trainingapp.R
import com.example.trainingapp.ui.daftar.DaftarActivity
import kotlinx.android.synthetic.main.activity_syarat.*

class SyaratActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syarat)
        syarat_back_button.setOnClickListener {
            finish()
        }
        syarat_sv.viewTreeObserver.addOnScrollChangedListener {
            if (syarat_sv.getChildAt(0).bottom == (syarat_sv.height + syarat_sv.scrollY)) {
                syarat_sv.setPadding(0,0,0,dpToPixels(50))
                syarat_sv.fullScroll(View.FOCUS_DOWN)
                agree_btn.visibility = View.VISIBLE

            } else {
                //scroll view is not at bottom
            }
        }
        agree_btn.setOnClickListener{
            setResult(RESULT_OK, Intent().putExtra("setuju", true))
            finish()
        }
    }

    fun dpToPixels(value: Int): Int {
        val scale: Float = Resources.getSystem().displayMetrics.density
        return (value * scale + 0.5f).toInt()

    }
}
