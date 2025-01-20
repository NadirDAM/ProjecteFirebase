package com.example.examennadirelhaidouri.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Mobles::class],
    version = 1,
    exportSchema = false
)
abstract class dataBase : RoomDatabase(){

    abstract fun moblesDao() : MoblesDAO
    companion object {

        @Volatile
        private var INSTANCE: dataBase? = null

        fun getDatabase(context: Context): dataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): dataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                dataBase::class.java,
                "mobles_database.db"
            ).createFromAsset("databases/mobles_database.db")
                .build()
        }
    }

}