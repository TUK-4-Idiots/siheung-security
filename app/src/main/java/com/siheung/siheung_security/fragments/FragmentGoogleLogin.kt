package com.siheung.siheung_security.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.siheung.siheung_security.LoginActivity
import com.siheung.siheung_security.databinding.FragmentGoogleLoginBinding

class FragmentGoogleLogin: Fragment() {
    private lateinit var binding: FragmentGoogleLoginBinding
    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }
    private val googleAuthLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)

            val userName = account.givenName
            val email = account.email


            moveSignUpActivity()
        } catch (e: ApiException) {
            Log.e(FragmentGoogleLogin::class.java.simpleName, e.stackTraceToString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoogleLoginBinding.inflate(layoutInflater, container, false)
        addListener()
        return binding.root
    }

    private fun addListener() {
        binding.googleLogin.setOnClickListener {
            requestGoogleLogin()
        }
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode("867671642523-3fhv6o8mt40gug95jcp5q65ieekcpr89.apps.googleusercontent.com")
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), googleSignInOption)
    }

    private fun moveSignUpActivity() {
        requireActivity().run {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            finish()
        }
    }
}