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
import com.example.examennadirelhaidouri.Room.Mobles
import com.example.examennadirelhaidouri.ViewModel.HomeFragmentViewModel
import com.example.examennadirelhaidouri.ViewModel.InsertarFragmentViewModel
import com.example.examennadirelhaidouri.databinding.FragmentHomeBinding
import com.example.examennadirelhaidouri.databinding.FragmentInsertBinding

class InsertFragment : Fragment() {

    private val viewModel: InsertarFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentInsertBinding.inflate(inflater)


        binding.button2.setOnClickListener {
            val nom = binding.editTextNom.text.toString()
            val preu = binding.editTextPreu.text.toString()

            if (nom.isNotEmpty() && preu.isNotEmpty()) {
                viewModel.afegirMoble(requireContext(), Mobles(nom, preu.toFloat()))
                Toast.makeText(requireContext(), "Moble creat correctament", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_insertFragment_to_homeFragment)

            }
            else {
                Toast.makeText(requireContext(), "Hi ha camps buits", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

}