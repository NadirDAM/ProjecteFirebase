package com.example.examennadirelhaidouri.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.examennadirelhaidouri.R
import com.example.examennadirelhaidouri.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val binding = FragmentLoginBinding.inflate(inflater)


        binding.textViewResetPassword.setOnClickListener {
            val email: String = binding.editTextUser.text.toString().trim()

            if (email.isEmpty()) {
                binding.editTextUser.error = "Please enter your email"
            } else {
                sendPasswordResetEmail(email)
            }
        }

        binding.outlinedButtonGoToRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        auth = FirebaseAuth.getInstance()
        binding.outlinedButtonLogin.setOnClickListener {
            val email = binding.editTextUser.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), " Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Password reset email sent", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Error sending email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}