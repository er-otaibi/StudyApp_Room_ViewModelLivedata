package com.example.studyapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "KotlinMaterials")
data class KotlinMaterials (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id : Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Description") val description: String,
    @ColumnInfo(name = "Details") val details: String
)

@Entity(tableName = "AndroidMaterials")
data class AndroidMaterials (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id : Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Description") val description: String,
    @ColumnInfo(name = "Details") val details: String
)