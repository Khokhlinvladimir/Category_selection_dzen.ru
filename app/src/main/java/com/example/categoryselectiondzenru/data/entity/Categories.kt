package com.example.categoryselectiondzenru.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp


@Entity(tableName = "categories_table")
data class Categories(
    @PrimaryKey
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long)
