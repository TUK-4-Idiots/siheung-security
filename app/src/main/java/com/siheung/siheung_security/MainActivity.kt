package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.siheung.siheung_security.FirstViewActivity
import com.siheung.siheung_security.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        moveMain(3) // 3초 후에 FirstViewActivity로 이동
    }

    private fun moveMain(sec: Int) {
        Handler().postDelayed({
            val intent = Intent(this, FirstViewActivity::class.java)
            startActivity(intent) // intent에 명시된 액티비티로 이동
            finish() // 현재 액티비티 종료
        }, 1000 * sec.toLong()) // sec초 정도 딜레이를 준 후 시작
    }
}
