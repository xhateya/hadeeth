package com.xhateya.idn.hadeeth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class OnBoardingOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)
        supportActionBar?.hide()

        Handler().postDelayed({
            startActivity(Intent(this@OnBoardingOne , OnBoardingTwo::class.java ))
            finish()
        },2500)
    }
}