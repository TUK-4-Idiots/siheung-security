package com.siheung.siheung_security

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnboardingActivity : AppCompatActivity() {
    // 타이틀, 설명, 이미지 리스트 초기화
    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()
    lateinit var signuptext:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        signuptext = findViewById(R.id.signupText)
        val intent = Intent(this, TermsOfUseActivity::class.java)
        signuptext.setOnClickListener{startActivity(intent)}

        // XML에서 뷰 요소들을 가져옵니다.
        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewpager2)

        // 어댑터를 생성하고 ViewPager2에 설정합니다.
        val adapter = ViewpagerAdapter(titleList, descList, imagesList) // ViewpagerAdapter를 초기화합니다.
        viewPager2.adapter = adapter // 어댑터를 ViewPager2에 연결합니다.
        dotsIndicator.setViewPager2(viewPager2) // DotsIndicator를 ViewPager2와 연결합니다.

        postToList() // 리스트에 데이터를 추가합니다.
    }

    // 리스트에 아이템을 추가하는 함수
    private fun addToList(title: String, description: String, image: Int) {
        titleList.add(title) // 타이틀 추가
        descList.add(description) // 설명 추가
        imagesList.add(image) // 이미지 추가
    }

    // 데이터를 리스트에 추가하는 함수
    private fun postToList() {
        addToList("김범수 아님", "", R.drawable.bum)
        addToList("범석원 짤", "", R.drawable.bum2)
        addToList("범일리언", "", R.drawable.bum3)
        addToList("벌에 쏘인 범수", "", R.drawable.bum4)
    }
}



