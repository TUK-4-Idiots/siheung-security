package com.siheung.siheung_security

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.ComponentActivity
import com.siheung.siheung_security.databinding.ActivityTermsOfUseBinding


private lateinit var binding : ActivityTermsOfUseBinding
class TermsOfUseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allAgree.setOnClickListener { onCheckChanged(binding.allAgree) }
        binding.guardAgree.setOnClickListener { onCheckChanged(binding.guardAgree) }
        binding.personalInfoAgree.setOnClickListener { onCheckChanged(binding.personalInfoAgree) }
        binding.thirdPartyAgree.setOnClickListener { onCheckChanged(binding.thirdPartyAgree) }
        binding.marketingAgree.setOnClickListener { onCheckChanged(binding.marketingAgree) }

    }

    private fun onCheckChanged(compoundButton: CompoundButton) {
        when(compoundButton) {
            binding.allAgree -> {
                if (binding.allAgree.isChecked) {
                    binding.guardAgree.isChecked = true
                    binding.personalInfoAgree.isChecked = true
                    binding.thirdPartyAgree.isChecked = true
                    binding.marketingAgree.isChecked = true
                }else {
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
