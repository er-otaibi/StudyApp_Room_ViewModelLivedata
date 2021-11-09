package com.example.studyapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KotlinMaterials::class,AndroidMaterials::class],version = 1,exportSchema = false)
abstract class MaterialsDatabase: RoomDatabase() {

    companion object{
        var instance:MaterialsDatabase?=null;
        fun getInstance(ctx: Context):MaterialsDatabase
        {
            if(instance!=null)
            {
                return  instance as MaterialsDatabase;
            }
            instance= Room.databaseBuilder(ctx,MaterialsDatabase::class.java,"materials").run { allowMainThreadQueries() }.build()
            return instance as MaterialsDatabase;
        }
    }
    abstract fun materialsDao():MaterialsDao;
}