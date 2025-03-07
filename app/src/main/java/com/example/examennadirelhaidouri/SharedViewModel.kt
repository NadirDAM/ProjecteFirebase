package com.example.examennadirelhaidouri

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _moble_editat: Mobles?=null
    val moble_editat: Mobles?
        get()=_moble_editat


    fun modifcarAlumne(moble : Mobles) {
        _moble_editat = moble
    }
}