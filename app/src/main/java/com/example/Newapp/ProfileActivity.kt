package com.example.Newapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Back to Home
        val homeButton = findViewById<ImageButton>(R.id.imageButton)
        homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }

        // Initialize views
        firstNameEditText = findViewById(R.id.editTextText)
        lastNameEditText = findViewById(R.id.editTextText2)
        emailEditText = findViewById(R.id.EmailAddress)
        phoneEditText = findViewById(R.id.editTextPhone)
        submitButton = findViewById(R.id.button)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        // Load saved user data
        loadUserData()

        // Save data on button click
        submitButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("FirstName", firstName)
            editor.putString("LastName", lastName)
            editor.putString("Email", email)
            editor.putString("Phone", phone)
            editor.apply()

            Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserData() {
        val firstName = sharedPreferences.getString("FirstName", "")
        val lastName = sharedPreferences.getString("LastName", "")
        val email = sharedPreferences.getString("Email", "")
        val phone = sharedPreferences.getString("Phone", "")

        firstNameEditText.setText(firstName)
        lastNameEditText.setText(lastName)
        emailEditText.setText(email)
        phoneEditText.setText(phone)
    }
}
