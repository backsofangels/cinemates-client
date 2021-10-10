package com.salvatore.cinemates

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.fasterxml.jackson.databind.ObjectMapper
import com.salvatore.cinemates.databinding.ActivityMainBinding
import com.salvatore.cinemates.model.CinematesUser
import com.salvatore.cinemates.view.movies.DiscoverFragment
import com.salvatore.cinemates.view.auth.UserAuthLoginFragment
import com.salvatore.cinemates.view.auth.UserProfileFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, this.binding.mainActivityDrawerLayout, 0, 0)
        this.binding.mainActivityDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle.syncState()
        this.binding.mainActivityNavigationView.setNavigationItemSelectedListener { menuItem ->
            var fragment: Fragment? = null
            when(menuItem.itemId) {
                R.id.drawer_menu_profile -> {
                    if (checkFieldAlreadyPresentInPrefs("userInfo")) {
                        Log.d(TAG, "Instantiating ProfileFragment")
                        fragment = UserProfileFragment()
                        val fragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentsPlaceholderView, fragment)
                        fragmentTransaction.commit()
                    } else {
                        Log.d(TAG, "Instantiating LoginFragment")
                        fragment = UserAuthLoginFragment()
                        val fragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentsPlaceholderView, fragment)
                        fragmentTransaction.commit()
                    }
                    this.binding.mainActivityDrawerLayout.closeDrawers()
                    true
                }
                R.id.drawer_menu_discover -> {
                    Log.d(TAG, "Instantiating DiscoverFragment")
                    fragment = DiscoverFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    Log.d(TAG, "beginning transaction to fragment")
                    fragmentTransaction.replace(R.id.fragmentsPlaceholderView, fragment)
                    fragmentTransaction.commit()
                    this.binding.mainActivityDrawerLayout.closeDrawers()
                    true
                }
                R.id.drawer_menu_lists -> {
                    Toast.makeText(this, "Lists", Toast.LENGTH_SHORT).show()
                    this.binding.mainActivityDrawerLayout.closeDrawers()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (this.binding.mainActivityDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.binding.mainActivityDrawerLayout.closeDrawer(GravityCompat.START)
        } else this.binding.mainActivityDrawerLayout.openDrawer(this.binding.mainActivityNavigationView)
        return true
    }

    override fun onBackPressed() {
        if (this.binding.mainActivityDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.binding.mainActivityDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun hideKeyboard(activity: AppCompatActivity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun saveUserInfoInSharedPrefs(user: CinematesUser) {
        val sharedPrefs = this.getPreferences(Context.MODE_PRIVATE)
        val jsonMapper = ObjectMapper()
        if (!sharedPrefs.contains("userInfo") ) {
            Log.d(TAG, "Saving user info in prefs")
            sharedPrefs.edit().putString("userInfo", jsonMapper.writeValueAsString(user)).apply()
        } else {
            Log.d(TAG, "Clearing prefs")
            sharedPrefs.edit().remove("userInfo").apply()
            Log.d(TAG, "Saving user info in prefs")
            sharedPrefs.edit().putString("userInfo", jsonMapper.writeValueAsString(user)).apply()
        }
    }

    fun saveJwtTokenInPrefs(token: String) {
        val sharedPrefs = this.getPreferences(Context.MODE_PRIVATE)
        if (!sharedPrefs.contains("authToken")) {
            Log.d(TAG, "Saving token $token")
            sharedPrefs.edit().putString("authToken", token).apply()
        } else {
            Log.d(TAG, "Clearing already present token")
            sharedPrefs.edit().remove("authToken").apply()
            Log.d(TAG, "Saving new token $token")
            sharedPrefs.edit().putString("authToken", token).apply()
        }
    }

    //Why i did this?
    fun checkFieldAlreadyPresentInPrefs(fieldName: String): Boolean {
        return this.getPreferences(Context.MODE_PRIVATE).contains(fieldName)
    }
}