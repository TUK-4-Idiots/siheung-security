package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.siheung.siheung_security.databinding.ActivityFirstViewBinding

class FirstViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstViewBinding // 뷰 바인딩을 위한 바인딩 변수
    lateinit var startbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_view)

        startbutton = findViewById(R.id.startbutton)
        val intent = Intent(this, OnboardingActivity::class.java)
        startbutton.setOnClickListener{startActivity(intent)}
    }
}