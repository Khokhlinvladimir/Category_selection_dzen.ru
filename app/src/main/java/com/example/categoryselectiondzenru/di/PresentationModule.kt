package com.example.categoryselectiondzenru.di

import android.content.Context
import com.example.categoryselectiondzenru.data.db.CatRoomDatabase
import com.example.categoryselectiondzenru.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PresentationModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope{
        return CoroutineScope(SupervisorJob())
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context, applicationScope: CoroutineScope): CatRoomDatabase{
        return CatRoomDatabase.getDatabase(context, scope = applicationScope)
    }

    @Provides
    @Singleton
    fun provideRepo(database: CatRoomDatabase): Repository{
        return Repository(database.catDao())
    }




}