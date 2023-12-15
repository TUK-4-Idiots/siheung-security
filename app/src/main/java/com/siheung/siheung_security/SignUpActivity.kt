package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.siheung.siheung_security.databinding.ActivitySignUpBinding
import com.siheung.siheung_security.fragments.ProgressFragment

private lateinit var binding : ActivitySignUpBinding

class SignUpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            intent = Intent(this, TermsOfUseActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(savedInstanceState == null) {
            // FragmentManager를 통해서 FragmentTransaction 획득하기
            val fragmentTransaction: FragmentTransaction =
                supportFragmentManager.beginTransaction()
            // add를 통해 container에 Fragment 추가
            fragmentTransaction.add(R.id.progressBar, ProgressFragment())
            fragmentTransaction.setReorderingAllowed(true)
            // commit을 통해 transaction 등록
            fragmentTransaction.commit()


//             FragmentKTX의 기능을 사용하여 위의 코드를 깔끔하게 변경
//             commit 함수 내부에 FragmentTransaction을 수신객체로 받는
//             함수 타입이 있어서 아래와 같이 작성 가능
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add(R.id.fragment_container, FirstFragment())
//            }
        }
    }


}