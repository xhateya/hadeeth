package com.xhateya.idn.hadeeth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class OnBoardingTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)
        supportActionBar?.hide()

        Handler().postDelayed({
            startActivity(Intent(this@OnBoardingTwo, OnBoardingThree::class.java))
            finish()
        }, 2500)
    }
}