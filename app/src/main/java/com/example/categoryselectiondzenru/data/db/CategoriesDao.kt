package com.example.categoryselectiondzenru.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.categoryselectiondzenru.data.entity.Categories
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

     @Query("SELECT * FROM categories_table ORDER BY category ASC")
    fun getCategories(): Flow<List<Categories>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categories: Categories)

    @Query("DELETE FROM categories_table")
    suspend fun deleteAll()

    @Query("DELETE FROM categories_table WHERE category = :str")
    suspend fun deleteByCategory(str: String)



}