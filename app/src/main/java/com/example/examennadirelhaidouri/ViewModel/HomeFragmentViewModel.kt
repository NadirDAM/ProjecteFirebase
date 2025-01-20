package com.example.examennadirelhaidouri.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.examennadirelhaidouri.Room.Mobles
import com.example.examennadirelhaidouri.Room.RepositoriMobles

class HomeFragmentViewModel : ViewModel() {

    private var _llistat_mobles: LiveData<List<Mobles>>?=null
    val llistat_mobles: LiveData<List<Mobles>>?
        get()=_llistat_mobles


    fun llistar_mobles(context: Context) {
        _llistat_mobles = RepositoriMobles.obtenirMobles(context)
    }
}