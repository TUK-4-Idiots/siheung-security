package com.siheung.siheung_security

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.siheung.siheung_security.databinding.ActivityFirstViewBinding
import com.siheung.siheung_security.databinding.ActivityOnboardingBinding
import com.siheung.siheung_security.databinding.ActivitySignUpCompleteBinding
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

private lateinit var binding : ActivityOnboardingBinding

class OnboardingActivity : FragmentActivity()  {
    // 타이틀, 설명, 이미지 리스트 초기화
    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    private lateinit var viewpager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signupText.setOnClickListener{
            val intent = Intent(this, TermsOfUseActivity::class.java)
            startActivity(intent)
            finish()
        }

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
        addToList("시흥시 치안,", "어떻게 생각하시나요?", R.drawable.onboarding1)
        addToList("이젠, 어플을 키세요", "문제가 발생 하면 도와드립니다", R.drawable.onboarding2)
        addToList("언제 어디서나", "도움을 요청 할 수 있고", R.drawable.onboarding3)
        addToList("가까운 방범대의", "위치정보를 표시해드립니다", R.drawable.onboarding4)
    }
}