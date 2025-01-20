package com.example.examennadirelhaidouri.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.examennadirelhaidouri.R
import com.example.examennadirelhaidouri.SharedViewModel
import com.example.examennadirelhaidouri.ViewModel.CrudFragmentViewModel
import com.example.examennadirelhaidouri.ViewModel.HomeFragmentViewModel
import com.example.examennadirelhaidouri.databinding.FragmentCrudBinding
import com.example.examennadirelhaidouri.databinding.FragmentHomeBinding


class CrudFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: CrudFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCrudBinding.inflate(inflater)

        val moble = sharedViewModel.moble_editat;
        binding.editTextNom.setText(moble?.nom.toString())
        binding.editTextPreu.setText(moble?.preu.toString())

        binding.buttonDel.setOnClickListener {
            viewModel.eliminarMoble(requireContext(), moble?.Id.toString().toLong())
        }

        binding.buttonUpd.setOnClickListener {
            if (moble != null) {
                viewModel.modificatMoble(requireContext(), binding.editTextNom.text.toString(), binding.editTextPreu.text.toString().toFloat(), moble?.Id.toString().toLong())
            }
        }


        return binding.root
    }


}