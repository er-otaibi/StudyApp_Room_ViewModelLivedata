package com.example.studyapp_sql.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.studyapp.AndroidMaterials
import com.example.studyapp.KotlinMaterials
import com.example.studyapp.MaterialsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {


    private val materialDao = MaterialsDatabase.getInstance(application).materialsDao()
    private val kotlinMaterials: LiveData<List<KotlinMaterials>> = materialDao.getKotlinMaterials()
    private val androidMaterials: LiveData<List<AndroidMaterials>> = materialDao.getAndroidMaterials()

    fun getKotlinLessons(): LiveData<List<KotlinMaterials>> {

        return kotlinMaterials
    }

    fun getAndroidLessons(): LiveData<List<AndroidMaterials>> {

        return androidMaterials
    }

     fun addKotlinLesson(lesson: KotlinMaterials){
        CoroutineScope(Dispatchers.IO).launch {

          materialDao.insertKotlinReview(lesson)
        }

    }

    fun addAndroidLesson(lesson: AndroidMaterials){
        CoroutineScope(Dispatchers.IO).launch {

           materialDao.insertAndroidReview(lesson)
        }
    }

    fun updateKotlinLesson(lesson: KotlinMaterials){
        CoroutineScope(Dispatchers.IO).launch {

            materialDao.updateKotlinReview(lesson)

        }
    }

    fun updateAndroidLesson(lesson: AndroidMaterials){
        CoroutineScope(Dispatchers.IO).launch {

            materialDao.updateAndroidReview(lesson)

        }
    }


    fun deleteKotlinLesson(lesson: KotlinMaterials){
        CoroutineScope(Dispatchers.IO).launch {

            materialDao.deleteKotlinReview(lesson)

        }
    }

    fun deleteAndroidLesson(lesson: AndroidMaterials){
        CoroutineScope(Dispatchers.IO).launch {

            materialDao.deleteAndroidReview(lesson)

        }
    }

}