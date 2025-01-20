package com.example.examennadirelhaidouri.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.examennadirelhaidouri.Room.Mobles
import com.example.examennadirelhaidouri.Room.RepositoriMobles

class InsertarFragmentViewModel : ViewModel() {

    fun afegirMoble(context: Context, moble: Mobles) {
        RepositoriMobles.afegirMoble(context, moble)
    }
}