package com.raya.agriapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainPage : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

//    }
//}
//        setSupportActionBar(toolbar)
//        supportActionBar?.title="agriculture App"

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinateLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationbar)
        setUpToolbar()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainPage,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.dashboard -> {
                    Toast.makeText(this@MainPage, "clicked on dashboard", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, dashboardPage::class.java)
                    startActivity(intent)
                }
                R.id.favorites -> {
                    Toast.makeText(this@MainPage, "clicked on favorites", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, favouritesPage::class.java)
                    startActivity(intent)
                }
                R.id.profile -> {
                    Toast.makeText(this@MainPage, "clicked on profile", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, profilePage::class.java)
                    startActivity(intent)
                }
                R.id.About -> {
                    Toast.makeText(this@MainPage, "clicked on about", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, aboutAppPage::class.java)
                    startActivity(intent)
                }

            }
            return@setNavigationItemSelectedListener true
        }

    }

    private fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Krishi info"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}
