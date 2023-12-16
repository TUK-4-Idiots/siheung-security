package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siheung.siheung_security.databinding.ActivityLoginBinding
import com.siheung.siheung_security.databinding.ActivitySignUpCompleteBinding

private lateinit var binding : ActivityLoginBinding


class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        //뒤로가기 버튼
        binding.backButton.setOnClickListener {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }
        //로그인 버튼
        binding.loginButton2.setOnClickListener {
            val intent = Intent(this, SignUpCompleteActivity::class.java)
            startActivity(intent)
            finish()
        }
        //아이디 찾기 버튼
        binding.lostId.setOnClickListener {
            val intent = Intent(this, SignUpCompleteActivity::class.java)
            startActivity(intent)
            finish()
        }
        //비밀번호 찾기 버튼
        binding.lostPassword.setOnClickListener {
            val intent = Intent(this, SignUpCompleteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}