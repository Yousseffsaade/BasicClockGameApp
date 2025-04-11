package com.example.basicclockgameapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ThirdActivity : AppCompatActivity() {

    private lateinit var wheelView: ImageView
    private lateinit var resultText: TextView
    private lateinit var spinButton: Button
    private lateinit var retryButton: Button
    private lateinit var userChoiceText: TextView

    private var userChoice = 0
    private var currentRotation = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        // Récupère le choix de l'utilisateur
        userChoice = intent.getIntExtra("CHOICE", -1)

        // Initialise les vues
        wheelView = findViewById(R.id.wheelView)
        resultText = findViewById(R.id.resultText)
        spinButton = findViewById(R.id.spinButton)
        retryButton = findViewById(R.id.retryButton)
        userChoiceText = findViewById(R.id.userChoiceText)

        // Affiche le choix de l'utilisateur
        userChoiceText.text = "You chose: $userChoice"

        spinButton.setOnClickListener {
            spinWheel()
        }

        retryButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun spinWheel() {
        spinButton.isEnabled = false // désactive pour éviter double clic
        resultText.text = ""
        retryButton.visibility = Button.GONE

        val selectedNumber = Random.nextInt(1, 13)
        val targetAngle = (12 - selectedNumber) * 30f
        val rotateTo = (360 * 5 + targetAngle)

        val rotateAnimation = RotateAnimation(
            currentRotation,
            currentRotation + rotateTo,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 3000
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()

        wheelView.startAnimation(rotateAnimation)
        currentRotation = (currentRotation + rotateTo) % 360

        Handler(Looper.getMainLooper()).postDelayed({
            resultText.text = "Spinner chose: $selectedNumber"
            if (selectedNumber == userChoice) {
                resultText.append("\n🎉 You won!")
            } else {
                resultText.append("\n❌ You lost!")
            }
            retryButton.visibility = Button.VISIBLE
            spinButton.isEnabled = true
        }, 3000)
    }
}
