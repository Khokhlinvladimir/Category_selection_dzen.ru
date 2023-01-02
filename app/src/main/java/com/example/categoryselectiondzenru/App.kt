package com.example.categoryselectiondzenru

import android.app.Application
import com.example.categoryselectiondzenru.data.repository.Repository
import com.example.categoryselectiondzenru.data.db.CatRoomDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy {
      CatRoomDatabase.getDatabase(this, scope = applicationScope)
    }

    val repository by lazy {
        Repository(database.catDao())
    }




}