package com.raya.agriapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

//import android.widget.FrameLayout
//import android.widget.LinearLayout
//import android.widget.Toast
//import androidx.appcompat.widget.Toolbar
//import androidx.cardview.widget.CardView
//import androidx.coordinatorlayout.widget.CoordinatorLayout
//import androidx.drawerlayout.widget.DrawerLayout
//import com.google.android.material.navigation.NavigationView

class dashboardPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_page)


        val cropInfo: CardView = findViewById(R.id.cardCrop)
        cropInfo.setOnClickListener {
            val intent = Intent(this, cropInfoPage::class.java)
            startActivity(intent)
        }
        val Storage: CardView = findViewById(R.id.cardStorage)
        Storage.setOnClickListener {
            val intent = Intent(this, storagePage::class.java)
            startActivity(intent)
        }

        val weather: CardView = findViewById(R.id.cardWeather)
        weather.setOnClickListener {
            val intent = Intent(this, WeatherPage::class.java)
            startActivity(intent)
        }
        val schemes: CardView = findViewById(R.id.cardSchemes)
        schemes.setOnClickListener {
            val intent = Intent(this, schemesPage::class.java)
            startActivity(intent)
        }






    }
    }
