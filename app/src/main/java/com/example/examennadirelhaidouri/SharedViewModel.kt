package com.example.examennadirelhaidouri

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.examennadirelhaidouri.Room.Mobles

class SharedViewModel : ViewModel() {

    private var _moble_editat: Mobles?=null
    val moble_editat: Mobles?
        get()=_moble_editat


    fun modifcarAlumne(moble : Mobles) {
        _moble_editat = moble
    }
}