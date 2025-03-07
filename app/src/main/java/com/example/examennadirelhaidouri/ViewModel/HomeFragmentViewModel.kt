package com.example.examennadirelhaidouri.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examennadirelhaidouri.Mobles
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragmentViewModel : ViewModel() {
    private lateinit var db: FirebaseFirestore
    private var _llistat_mobles: MutableLiveData<List<Mobles>> = MutableLiveData()
    val llistat_mobles: LiveData<List<Mobles>> get() = _llistat_mobles

    fun llistar_mobles() {
        db = FirebaseFirestore.getInstance()

        val productos = mutableListOf<Mobles>()
        db.collection("productes")
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot != null) {
                    for (document in querySnapshot.documents) {
                        val documentId = document.id
                        val data = document.data

                        val uid = documentId
                        val nom = data?.get("nom") as? String ?: ""
                        val preu = data?.get("preu") as? Double ?: 0.0

                        val producto = Mobles(uid, nom, preu)
                        productos.add(producto)
                    }
                }

                _llistat_mobles.value = productos
            }
            .addOnFailureListener { exception ->
                println("Error al obtener documentos: $exception")
            }
    }
}
