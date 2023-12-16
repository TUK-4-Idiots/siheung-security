package com.siheung.siheung_security

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.google.android.material.internal.TextWatcherAdapter
import com.siheung.siheung_security.databinding.ActivitySignUpBinding
import com.siheung.siheung_security.fragments.FragmentButton



class SignUpActivity: AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

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
                if (s != null) {
                    viewModel.updateLength(s.length)
                }
            }
        })

        viewModel.getProgress().observe(this, Observer { progress ->
            var startPoint = progress - 30

            binding.progressBar.progress = progress
            animateProgressBar(startPoint.toFloat(), progress.toFloat())
            binding.progressBarPercent.text = "$progress%"
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
        anim.duration = 1000
        progressBar.startAnimation(anim)
    }

}