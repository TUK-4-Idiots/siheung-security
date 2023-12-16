package com.siheung.siheung_security

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.google.android.material.internal.TextWatcherAdapter
import com.siheung.siheung_security.databinding.ActivitySignUpBinding
import com.siheung.siheung_security.fragments.FragmentButton
import java.util.regex.Pattern


class SignUpActivity: AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    private val emailPattern = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"
    private val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            intent = Intent(this, TermsOfUseActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.nameInput.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                viewModel.updateNameLength(s.length)
            }
        })

        binding.emailInput.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable) {
                viewModel.updateEmail(s.toString())
                if (Pattern.matches(emailPattern, s)) {
                    binding.emailInputGuide.visibility = View.GONE
                }
                else {
                    binding.emailInputGuide.visibility = View.VISIBLE
                }
            }
        })

        binding.passwordInput.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable) {
                viewModel.updatePasswd(s.toString())
                if (Pattern.matches(pwPattern, viewModel.getPasswd().value)) {
                    binding.passwordInputGuide.visibility = View.GONE
                }
                else {
                    binding.passwordInputGuide.visibility = View.VISIBLE
                }
            }
        })

        binding.verifyPasswordInput.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable) {
                viewModel.updateVerifyPasswd(s.toString())
                if (viewModel.getPasswd().value == viewModel.getVerifyPasswd().value) {
                    binding.verifyPasswordInputGuide.text = "비밀번호가 일치합니다."
                    binding.verifyPasswordInputGuide.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.valid))
                }
                else {
                    binding.verifyPasswordInputGuide.visibility = View.VISIBLE
                    binding.verifyPasswordInputGuide.text = "비밀번호가 일치하지 않습니다."
                    binding.verifyPasswordInputGuide.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.invalid))
                }
            }
        })

        viewModel.getProgress().observe(this, Observer { progress ->
            val startPoint = if (progress <= 0) 0 else progress - 33

            binding.progressBar.progress = progress
            animateProgressBar(startPoint.toFloat(), progress.toFloat())
            startAnimation(binding.progressBarPercent, startPoint, progress)

            when(binding.progressBar.progress) {
                33 -> {
                    binding.signUpActivityTitle.text = "이메일을 입력해주세요"
                    binding.emailInputLayout.visibility = View.VISIBLE
                }
                66 -> {
                    binding.signUpActivityTitle.text = "비밀번호를 입력해주세요"
                    binding.passwordInputLayout.visibility = View.VISIBLE
                    binding.verifyPasswordInputLayout.visibility = View.VISIBLE
                }
            }
        })


        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.nextButton, FragmentButton())
            }
        }
    }

    private fun animateProgressBar(startPoint: Float, endPoint: Float) {
                    val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                    val anim = AnimateProgressBar(progressBar, startPoint, endPoint)
                    anim.duration = 1500
                    progressBar.startAnimation(anim)
                }
    private fun startAnimation(textView: TextView, startPoint: Int, endPoint: Int) {
        val animator = ValueAnimator.ofInt(startPoint, endPoint)
        animator.duration = 1500
        animator.addUpdateListener { animation ->
            textView.text = animation.animatedValue.toString() + "%"
        }
        animator.start()
    }
}
