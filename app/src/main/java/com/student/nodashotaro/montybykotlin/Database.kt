package com.student.nodashotaro.montybykotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class Database(ctx: Context):ManagedSQLiteOpenHelper(ctx,"DataBase",null,1) {
    companion object {
        private var instance: Database?=null

        @Synchronized
        fun getInstance(ctx: Context): Database {
            if (instance == null) {
                instance = Database(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable("HighScore", true,
                "score" to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable("User", true)
    }
}

// Access property for Context
val Context.database: Database
    get() = Database.getInstance(getApplicationContext())