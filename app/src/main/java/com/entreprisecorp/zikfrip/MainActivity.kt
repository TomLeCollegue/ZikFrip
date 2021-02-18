package com.entreprisecorp.zikfrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageUser = findViewById<ImageView>(R.id.imageUser);
        val popUpUser = findViewById<CardView>(R.id.PopUpUser);
        val buttonOrders = findViewById<TextView>(R.id.textOrder)
        val fragment = findViewById<View>(R.id.fragment2)
        val imageLogo = findViewById<ImageView>(R.id.imageLogo)
        val backgroundPopUp = findViewById<ConstraintLayout>(R.id.popupLayout)


        imageUser.setOnClickListener {
            if (popupLayout.visibility == View.VISIBLE) {
                popupLayout.visibility = View.GONE;
            } else {
                popupLayout.visibility = View.VISIBLE;
            }
        }

        popupLayout.setOnClickListener {
            popupLayout.visibility = View.GONE
        }

        imageLogo.setOnClickListener {
            Navigation.findNavController(fragment)
                .navigate(R.id.action_global_homeFragment);
        }

        buttonOrders.setOnClickListener {
            Navigation.findNavController(fragment)
                .navigate(R.id.action_global_ordersFragment);
            popupLayout.visibility = View.GONE
        }


    }
}