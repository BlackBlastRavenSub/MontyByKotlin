package com.student.nodashotaro.montybykotlin

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.*
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk25.coroutines.onClick

class TitleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(30)
            val test = editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            button("Input") {
                textSize = 26f
                onClick {
                    database.use {
                        insert("HighScore",
                                "score" to 20
                        )
                    }
                }
            }
            button("Putout") {
                textSize = 26f
                onClick {
                    db.select//dbが使えない？
                    val test:String=database.use {
                         select("HighScore", "score")
                        //Stringに出来ないからデータを見時として画面に表示できない・・
                    }
                    ctx.toast(test)
                }
            }
            textView("Hello")
        }
    }
}
