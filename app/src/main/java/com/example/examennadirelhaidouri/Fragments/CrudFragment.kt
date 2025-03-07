package com.example.examennadirelhaidouri.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.examennadirelhaidouri.R
import com.example.examennadirelhaidouri.SharedViewModel
import com.example.examennadirelhaidouri.databinding.FragmentCrudBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CrudFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var currentProductId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCrudBinding.inflate(inflater)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val moble = sharedViewModel.moble_editat
        binding.editTextNom.setText(moble?.nom)
        binding.editTextPreu.setText(moble?.preu.toString())

        currentProductId = moble?.uid

        binding.buttonDel.setOnClickListener {
            if (currentProductId != null) {
                deleteProduct(currentProductId!!)
            } else {
                Toast.makeText(requireContext(), "No product selected", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón Actualizar
        binding.buttonUpd.setOnClickListener {
            val nom = binding.editTextNom.text.toString().trim()
            val preu = binding.editTextPreu.text.toString().trim().toFloat()

            if (nom.isNotEmpty() && preu > 0) {
                if (currentProductId != null) {
                    updateProduct(currentProductId!!, nom, preu)
                } else {
                    Toast.makeText(requireContext(), "No product selected", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please complete all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun updateProduct(productId: String, nom: String, preu: Float) {
        val userId = auth.currentUser?.uid
        val updatedProduct = hashMapOf(
            "uid" to userId,
            "nom" to nom,
            "preu" to preu
        )
System.out.println(productId)
        db.collection("productes").document(productId)
            .update(updatedProduct as Map<String, Double>)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Product updated successfully!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_crudFragment_to_homeFragment)
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error updating product: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteProduct(productId: String) {
        db.collection("productes").document(productId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Product deleted successfully!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_crudFragment_to_homeFragment)
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error deleting product: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
