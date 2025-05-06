package com.example.Newapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.activity.enableEdgeToEdge

@SuppressLint("CustomHomeScreen")
class HomeActivity : AppCompatActivity() {

    private lateinit var menuButton: ImageButton

    @SuppressLint("MissingInflatedId", "UseKtx")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        menuButton = findViewById(R.id.menuButton)
        menuButton.setOnClickListener { showPopupMenu(it) }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_project -> {
                    Toast.makeText(this, "Project clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_settings -> {
                    val settingsItemView = bottomNav.findViewById<View>(R.id.nav_settings)
                    showSettingsSubMenu(settingsItemView)
                    true
                }
                else -> false
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_project -> {
                    Toast.makeText(this, "Project clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_settings -> {
                    showSettingsSubMenu(view)
                    true
                }
                else -> false
            }
        }

        popup.show()
    }

    private fun showSettingsSubMenu(view: View) {
        val settingsPopup = PopupMenu(this, view)
        settingsPopup.menuInflater.inflate(R.menu.settings_submenu, settingsPopup.menu)

        settingsPopup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_app_settings -> {
                    val intent = Intent(this, AppSettings::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_orders -> {
                    val intent = Intent(this, PaymentActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_about -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        settingsPopup.show()
    }
}
