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
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest//バナー広告
import com.google.android.gms.ads.AdView//バナー広告



class TitleActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mStorageRef: StorageReference? = null
    val key = "color"
    val value = "blue"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111")
        mAdView = find(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mStorageRef = FirebaseStorage.getInstance().getReference()

        val database = FirebaseDatabase.getInstance()
        val ref_name = database.getReference("name")//key
        ref_name.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("onCancelled", "error:", error.toException())
            }
        })
        val ref_pass = database.getReference("pass")
        ref_pass.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("onCancelled", "error:", error.toException())
            }
        })


        verticalLayout {
            padding = dip(30)
            val name = editText {
                hint = "Name"
                textSize = 24f
            }
            val pass= editText {
                hint = "Password"
                textSize = 24f
                textColor = Color.RED
            }
            button("Input") {
                textSize = 26f
                onClick {
                    ref_name.setValue("${name.text}")
                    ref_pass.setValue("${pass.text}")
                }
            }.lparams(width = matchParent) {
                horizontalMargin = dip(5)
                topMargin = dip(10)

            }
            button("Putout") {
                textSize = 26f
                onClick {
                    this@button.text = name.toString()
                }
            }.lparams(width = matchParent) {
                horizontalMargin = dip(5)
                topMargin = dip(10)
            }

        }
    }
}