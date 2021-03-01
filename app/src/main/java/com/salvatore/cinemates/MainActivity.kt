package com.salvatore.cinemates

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.salvatore.cinemates.view.DiscoverFragment

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private var TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.mainActivityDrawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarDrawerToggle.syncState()

        navigationView = findViewById(R.id.mainActivityNavigationView)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            var fragment: Fragment? = null

            when(menuItem.itemId) {
                R.id.drawer_menu_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    this.drawerLayout.closeDrawers()
                    true
                }
                R.id.drawer_menu_discover -> {
                    Log.d(TAG, "Instantiating DiscoverFragment")
                    fragment = DiscoverFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    Log.d(TAG, "beginning transaction to fragment")
                    fragmentTransaction.add(R.id.mainActivityDrawerLayout, fragment)
                    fragmentTransaction.commit()
                    this.drawerLayout.closeDrawers()
                    true
                }
                R.id.drawer_menu_lists -> {
                    Toast.makeText(this, "Lists", Toast.LENGTH_SHORT).show()
                    this.drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else drawerLayout.openDrawer(navigationView)
        return true
    }

    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}