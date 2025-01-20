package com.example.examennadirelhaidouri.Room

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RepositoriMobles {

    companion object {
        var repositori_database: dataBase? = null

        var mobles: LiveData<List<Mobles>>? = null

        //funció per a inicialitzar la BD

        fun initializeDB(context: Context): dataBase {
            return dataBase.getDatabase(context)
        }

        fun afegirMoble(context: Context, moble: Mobles) {

            //Connectar la BD
            repositori_database = initializeDB(context)

            CoroutineScope(IO).launch {
                repositori_database!!.moblesDao().afegirMoble(moble)
            }
        }

        fun obtenirMobles(context: Context): LiveData<List<Mobles>>? {

            //Connectar la BD
            repositori_database = initializeDB(context)

            CoroutineScope(IO).launch {
                mobles = repositori_database!!.moblesDao().obtenirMobles()
            }

            return mobles
        }

        fun modificarMoble(context: Context, nom: String, preu: Float, id: Long) {

            //Connectar la BD
            repositori_database = initializeDB(context)

            CoroutineScope(IO).launch {
                repositori_database!!.moblesDao().modificarMoble(nom,preu,id)
            }
        }

        fun eliminarMoble(context: Context, id: Long) {

            //Connectar la BD
            repositori_database = initializeDB(context)

            CoroutineScope(IO).launch {
                repositori_database!!.moblesDao().eliminarMoble(id)
            }
        }
    }
}