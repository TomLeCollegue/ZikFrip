package com.entreprisecorp.zikfrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageUser = findViewById<ImageView>(R.id.imageUser);
        val popUpUser = findViewById<CardView>(R.id.PopUpUser);


        imageUser.setOnClickListener(){
            if (popUpUser.visibility == View.VISIBLE) {
                popUpUser.visibility = View.GONE;
            } else {
                popUpUser.visibility = View.VISIBLE;
            }
        }
    }
}