package com.example.basicclockgameapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Récupère nom/prénom depuis l'Intent
        val firstName = intent.getStringExtra("FIRST_NAME")
        val lastName = intent.getStringExtra("LAST_NAME")

        // Affiche le message de bienvenue
        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        welcomeText.text = "Welcome $firstName $lastName"

        // Configure les 12 boutons de 1 à 12
        for (i in 1..12) {
            val buttonId = resources.getIdentifier("btn$i", "id", packageName)
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("CHOICE", i)
                startActivity(intent)
            }
        }
    }
}
