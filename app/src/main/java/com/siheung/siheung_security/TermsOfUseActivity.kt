package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.siheung.siheung_security.databinding.ActivityTermsOfUseBinding


private lateinit var binding : ActivityTermsOfUseBinding
class TermsOfUseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this, SignUpActivity::class.java)

        binding.allAgree.setOnClickListener { onCheckChanged(binding.allAgree) }
        binding.guardAgree.setOnClickListener { onCheckChanged(binding.guardAgree) }
        binding.personalInfoAgree.setOnClickListener { onCheckChanged(binding.personalInfoAgree) }
        binding.thirdPartyAgree.setOnClickListener { onCheckChanged(binding.thirdPartyAgree) }
        binding.marketingAgree.setOnClickListener { onCheckChanged(binding.marketingAgree) }
        
        binding.backButton.setOnClickListener {
            intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.nextButton.setOnClickListener{
            if (binding.allAgree.isChecked && binding.ageOver.isChecked) {
                startActivity(intent)
                finish()
            }
            else if (binding.allAgree.isChecked && !binding.ageBelow.isChecked)
                Toast.makeText(this, "만 14세 이상부터 사용 가능합니다.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "먼저 약관에 동의해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onCheckChanged(compoundButton: CompoundButton) {
        when (compoundButton) {
            binding.allAgree -> {
                if (binding.allAgree.isChecked) {
                    binding.guardAgree.isChecked = true
                    binding.personalInfoAgree.isChecked = true
                    binding.thirdPartyAgree.isChecked = true
                    binding.marketingAgree.isChecked = true
                } else {
                    binding.guardAgree.isChecked = false
                    binding.personalInfoAgree.isChecked = false
                    binding.thirdPartyAgree.isChecked = false
                    binding.marketingAgree.isChecked = false
                }
            }

            else -> {
                binding.allAgree.isChecked = (
                        binding.guardAgree.isChecked
                                && binding.personalInfoAgree.isChecked
                                && binding.thirdPartyAgree.isChecked
                                && binding.marketingAgree.isChecked)
            }
        }
    }

}