package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siheung.siheung_security.databinding.ActivitySignUpCompleteBinding
import com.siheung.siheung_security.databinding.ActivityTermsOfUseBinding


private lateinit var binding : ActivitySignUpCompleteBinding

class SignUpCompleteActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_complete)

        binding = ActivitySignUpCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.okayButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}