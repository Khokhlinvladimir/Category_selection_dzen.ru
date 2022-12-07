package com.example.categoryselectiondzenru.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.categoryselectiondzenru.data.entity.Categories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Categories::class], version = 1)

abstract class CatRoomDatabase : RoomDatabase() {

    abstract fun catDao(): CategoriesDao

    companion object {
        @Volatile
        private var INSTANCE: CatRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CatRoomDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(context.applicationContext, CatRoomDatabase::class.java, "word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}

