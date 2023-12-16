package com.siheung.siheung_security.fragments

import android.os.Bundle
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentButtonBinding.inflate(layoutInflater, container, false)

        viewModel.getLength().observe(viewLifecycleOwner, Observer { length ->
            Log.d("tag", "$length")
            if (length >= 2) {
                binding.nextButton.isEnabled = true
                binding.nextButton.setBackgroundResource(R.drawable.button_background_blue)
            }
            else {
                binding.nextButton.isEnabled = false
                binding.nextButton.setBackgroundResource(R.drawable.button_background_disabled)
            }
        })

        binding.nextButton.setOnClickListener {
            viewModel.nextStep()
        }
        return binding.root
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}