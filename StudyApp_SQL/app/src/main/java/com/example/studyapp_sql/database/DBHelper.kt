package com.example.studyapp_sql.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.studyapp_sql.model.Lesson

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Review.db", null, 1) {

    private var sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("create table kotlinReview (Id INTEGER PRIMARY KEY AUTOINCREMENT, Title text , Description text , Details text )")

        db?.execSQL("create table AndroidReview (Id INTEGER PRIMARY KEY AUTOINCREMENT, Title text , Description text , Details text )")

    }

    override fun onUpgrade(dp: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion && dp != null) {

            dp.execSQL("DROP TABLE IF EXISTS kotlinReview")
            dp.execSQL("DROP TABLE IF EXISTS AndroidReview")
            onCreate(dp)
        }
    }

    @SuppressLint("Range")
    fun readAndroidLessons() : ArrayList<Lesson> {
        var selectQuery = "SELECT  * FROM AndroidReview"
        var cursor: Cursor? = null
        try {

            cursor = sqLiteDatabase.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            sqLiteDatabase.execSQL(selectQuery)
        }
        var androidList = ArrayList<Lesson>()
        var id = 0
        var title =""
        var description = ""
        var details =""
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getInt(cursor.getColumnIndex("Id"))
                    title = cursor.getString(cursor.getColumnIndex("Title"))
                    description = cursor.getString(cursor.getColumnIndex("Description"))
                    details = cursor.getString(cursor.getColumnIndex("Details"))

                    androidList.add(Lesson(id,title, description, details))
                } while (cursor.moveToNext())
            }
        }
        return androidList
    }

    @SuppressLint("Range")
    fun readKotlinLessons() : ArrayList<Lesson>{
        var selectQuery = "SELECT  * FROM kotlinReview"
        var cursor: Cursor? = null
        try {

            cursor = sqLiteDatabase.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            sqLiteDatabase.execSQL(selectQuery)
        }
        var kotlinList = ArrayList<Lesson>()

        var id = 0
        var title =""
        var description = ""
        var details =""
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getInt(cursor.getColumnIndex("Id"))
                    title = cursor.getString(cursor.getColumnIndex("Title"))
                    description = cursor.getString(cursor.getColumnIndex("Description"))
                    details = cursor.getString(cursor.getColumnIndex("Details"))

                    kotlinList.add(Lesson(id,title, description, details))
                } while (cursor.moveToNext())
            }
        }

        return kotlinList
    }

    fun insertKotlinLesson(lesson: Lesson) {

        val cv = ContentValues()
        cv.put("Title", lesson.title)
        cv.put("Description", lesson.description)
        cv.put("Details", lesson.details)

        sqLiteDatabase.insert("kotlinReview", null, cv)
    }

    fun insertAndroidLesson(lesson: Lesson) {

        val cv = ContentValues()
        cv.put("Title", lesson.title)
        cv.put("Description", lesson.description)
        cv.put("Details", lesson.details)

        sqLiteDatabase.insert("AndroidReview", null, cv)
    }

    fun updateKotlinLesson(lesson: Lesson) {
        val cv = ContentValues()
        cv.put("Title", lesson.title)
        cv.put("Description", lesson.description)
        cv.put("Details", lesson.details)
        sqLiteDatabase.update("kotlinReview" , cv , "Id=${lesson.id}" , null)

    }

    fun updateAndroidLesson(lesson: Lesson) {
        val cv = ContentValues()
        cv.put("Title", lesson.title)
        cv.put("Description", lesson.description)
        cv.put("Details", lesson.details)
        sqLiteDatabase.update("AndroidReview" , cv , "Id=${lesson.id}" , null)

    }

    fun deleteKotlinLesson(lesson: Lesson){
        sqLiteDatabase.delete("kotlinReview", "Id=${lesson.id}", null)
    }

    fun deleteAndroidLesson(lesson: Lesson){

        sqLiteDatabase.delete("AndroidReview", "Id=${lesson.id}", null)
    }
}