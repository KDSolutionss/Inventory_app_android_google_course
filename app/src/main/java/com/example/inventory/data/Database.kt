package com.example.inventory.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


@androidx.room.Database(entities=[Item::class],version=1, exportSchema = false)
abstract class ItemDatabase:RoomDatabase() {
    abstract fun itemDao(): ItemDao
    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null
        fun getDatabase(context: Context): ItemDatabase {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                context.applicationContext,
                ItemDatabase::class.java,
                "item_database"
            ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}