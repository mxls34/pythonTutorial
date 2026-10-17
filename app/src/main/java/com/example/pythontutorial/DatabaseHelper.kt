package com.example.pythontutorial

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context,
    "score.db",
    null,
    1
) {

    val DATABASE_NAME = "score.db"
    val DATABASE_VERSION: Int = 1
    val TABLE_NAME = "score_table"

    val COLUMN_ID = "id"
    val COLUMN_LESSON = "lesson_name"
    val COLUMN_SCORE = "lesson_score"


    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL(
            """
                CREATE TABLE $TABLE_NAME (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_LESSON TEXT,
                    $COLUMN_SCORE INTEGER                
                )
            """
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldV: Int,
        newV: Int
    ) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(lesson_name: String, lesson_score: Int){

        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_LESSON, lesson_name)
        values.put(COLUMN_SCORE, lesson_score)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun seeData(): Cursor{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun deleteAll() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }

}