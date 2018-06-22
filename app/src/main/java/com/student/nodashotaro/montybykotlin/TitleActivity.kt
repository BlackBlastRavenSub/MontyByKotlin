package com.student.nodashotaro.montybykotlin

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.*
import org.jetbrains.anko.db.createTable
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference





class TitleActivity : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mStorageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mStorageRef = FirebaseStorage.getInstance().getReference()
        val key = "color"
        val value = "red"
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference(key)
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("onCancelled", "error:", error.toException())
            }
        })


        verticalLayout {
            padding = dip(30)
            val test = editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
                textColor= Color.RED
            }
            button("Input") {
                textSize = 26f
                onClick {
                    ref.setValue(value)
                }
            }.lparams(width= matchParent){
                horizontalMargin=dip(5)
                topMargin=dip(10)

            }
            button("Putout") {
                textSize = 26f
                onClick {
                    this@button.text = value.toString()
                }
            }.lparams(width= matchParent){
                horizontalMargin=dip(5)
                topMargin=dip(10)
            }
        }
    }

}
