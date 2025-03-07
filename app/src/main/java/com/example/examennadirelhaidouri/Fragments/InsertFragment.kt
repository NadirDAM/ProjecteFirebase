package com.example.examennadirelhaidouri.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.examennadirelhaidouri.R
import com.example.examennadirelhaidouri.ViewModel.InsertarFragmentViewModel
import com.example.examennadirelhaidouri.databinding.FragmentInsertBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InsertFragment : Fragment() {

    private val viewModel: InsertarFragmentViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentInsertBinding.inflate(inflater)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.button2.setOnClickListener {
            val nom = binding.editTextNom.text.toString().trim()
            val preu = binding.editTextPreu.text.toString().trim().toFloat()

            if (nom.isNotEmpty() && preu > 0) {
                saveUserToFirestore( nom, preu)

            } else {
                Toast.makeText(requireContext(), "Please complete all fields", Toast.LENGTH_SHORT).show()
            }
        }



        return binding.root
    }

    private fun saveUserToFirestore(nom: String, preu: Float) {
        val userId = auth.currentUser?.uid
        val user = hashMapOf(
            "nom" to nom,
            "preu" to preu
        )

        if (userId != null) {
            db.collection("productes").document()
                .set(user)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Producte creat correctament!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_insertFragment_to_homeFragment)


                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Error saving data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

}