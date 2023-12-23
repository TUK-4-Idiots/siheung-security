package com.siheung.siheung_security.fragments

import java.util.regex.Pattern
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.siheung.siheung_security.SignUpViewModel
import com.siheung.siheung_security.R
import com.siheung.siheung_security.databinding.FragmentButtonBinding

class FragmentButton: Fragment() {
    private var _binding: FragmentButtonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by activityViewModels()
    private val emailPattern = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"
    private val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonBinding.inflate(layoutInflater, container, false)

        viewModel.getName().observe(viewLifecycleOwner, Observer { name ->
            if (name.length >= 2) {
                changeNextButtonState(true)
            }
            else {
                changeNextButtonState(false)
            }
        })

        viewModel.getEmail().observe(viewLifecycleOwner, Observer { email ->
            if (Pattern.matches(emailPattern, email)) {
                changeNextButtonState(true)
            }
            else {
                changeNextButtonState(false)
            }
        })

        viewModel.getPasswd().observe(viewLifecycleOwner, Observer { passwd ->
            if (passwd.length >= 8 && Pattern.matches(pwPattern, passwd) && passwd == viewModel.getVerifyPasswd().value) {
                changeNextButtonState(true)
            }
            else {
                changeNextButtonState(false)
            }
        })

        viewModel.getVerifyPasswd().observe(viewLifecycleOwner, Observer { verifyPasswd ->
            if (viewModel.getPasswd().value == verifyPasswd) {
                changeNextButtonState(true)
            }
            else {
                changeNextButtonState(false)
            }
        })

        binding.nextButton.setOnClickListener {
            viewModel.nextStep()
            changeNextButtonState(false)
        }
        return binding.root
    }

    private fun changeNextButtonState(able: Boolean) {
        if (able) {
            binding.nextButton.isEnabled = true
            binding.nextButton.setBackgroundResource(R.drawable.button_background_blue)
        }
        else {
            binding.nextButton.isEnabled = false
            binding.nextButton.setBackgroundResource(R.drawable.button_background_disabled)
        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}