package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.siheung.siheung_security.databinding.ActivityLoginBinding
import com.siheung.siheung_security.fragments.FragmentGoogleLogin


private lateinit var binding : ActivityLoginBinding

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = DBHelper(this, "siheung-security", null, 1)
        var database = dbHelper.readableDatabase

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // x 버튼을 누를 때 EditText 내용을 지우는 기능 추가


        supportActionBar?.hide()
        //뒤로가기 버튼
        binding.backButton.setOnClickListener {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }
        //로그인 버튼
        binding.loginButton2.setOnClickListener {
            val intent = Intent(this, MapViewActivity::class.java)
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


        binding.loginButton2.setOnClickListener {
            var inputId = binding.editId.text
            var inputPasswd = binding.editPw.text

            if (dbHelper.login(database, inputId.toString(), inputPasswd.toString())) {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MapViewActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                binding.editId.text = null
                binding.editPw.text = null
            }
        }

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.googleLogin, FragmentGoogleLogin())
            }
        }
    }


}

