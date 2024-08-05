package com.raya.agriapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class schemesPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schemes_page)

        val schemeInterest: CardView = findViewById(R.id.schemeInterest)
        val schemeUrea: CardView = findViewById(R.id.schemeUrea)
        val schemeKrishiVikas: CardView = findViewById(R.id.schemeKrishiVikas)
        val schemeFasalBhima: CardView = findViewById(R.id.schemeFasalBhima)

            schemeInterest.setOnClickListener {
                val schemeInterest = Intent(android.content.Intent.ACTION_VIEW)
                schemeInterest.data = Uri.parse("https://www.rbi.org.in/scripts/NotificationUser.aspx?Id=12411&Mode=0#mainsection")
                startActivity(schemeInterest)
            }

        schemeUrea.setOnClickListener {
            val schemeUrea = Intent(android.content.Intent.ACTION_VIEW)
            schemeUrea.data= Uri.parse("https://nationalfertilizers.com/index.php?option=com_content&view=article&id=139&Itemid=158&lang=en")
            startActivity(schemeUrea)
        }


       schemeKrishiVikas.setOnClickListener {
           val schemeKrishiVikas = Intent(android.content.Intent.ACTION_VIEW)
           schemeKrishiVikas.data= Uri.parse("https://dmsouthwest.delhi.gov.in/scheme/paramparagat-krishi-vikas-yojana/")
           startActivity(schemeKrishiVikas)
        }

        schemeFasalBhima.setOnClickListener {
            val schemeFasalBhima = Intent(android.content.Intent.ACTION_VIEW)
            schemeFasalBhima.data= Uri.parse("https://pmfby.gov.in/")
            startActivity(schemeFasalBhima)
        }
    }
}