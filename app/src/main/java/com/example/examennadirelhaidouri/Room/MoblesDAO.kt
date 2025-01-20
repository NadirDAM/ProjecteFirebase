package com.example.examennadirelhaidouri.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoblesDAO {

    @Insert(onConflict = OnConflictStrategy.NONE)
    fun afegirMoble(alumne: Mobles)

    @Query("SELECT * FROM Furnitures ORDER BY name DESC")
    fun obtenirMobles(): LiveData<List<Mobles>>

    @Query("UPDATE Furnitures SET name = :nom, price = :preu WHERE id = :id")
    fun modificarMoble(nom: String, preu: Float, id: Long)

    @Query("DELETE FROM Furnitures WHERE id = :id")
    fun eliminarMoble(id: Long)
}