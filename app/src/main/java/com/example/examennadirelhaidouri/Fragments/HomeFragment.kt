package com.example.examennadirelhaidouri.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examennadirelhaidouri.Adapters.CustomAdapter
import com.example.examennadirelhaidouri.R
import com.example.examennadirelhaidouri.SharedViewModel
import com.example.examennadirelhaidouri.ViewModel.HomeFragmentViewModel
import com.example.examennadirelhaidouri.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        viewModel.llistar_mobles(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.llistat_mobles?.observe(viewLifecycleOwner) { moblesLlistat ->
            binding.recyclerView.adapter = CustomAdapter(moblesLlistat) { item ->
                findNavController().navigate(R.id.action_homeFragment_to_crudFragment)
                sharedViewModel.modifcarAlumne(item)
            }
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_insertFragment)
        }

        return binding.root
    }

}