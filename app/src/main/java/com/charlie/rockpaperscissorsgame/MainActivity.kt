package com.charlie.rockpaperscissorsgame

import android.annotation.SuppressLint // Optional (see note below)
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random // Use Kotlin Random class

class MainActivity : AppCompatActivity() {

    //declare
    private lateinit var computerChoiceImage: ImageView
    private lateinit var rockButton: Button
    private lateinit var paperButton: Button
    private lateinit var scissorsButton: Button
    private lateinit var resultTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //initialise
        computerChoiceImage = findViewById(R.id.computerChoiceImage)
        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        scissorsButton = findViewById(R.id.scissorButton)
        resultTextView = findViewById(R.id.resultTextView)

        rockButton.setOnClickListener {
            playGame("rock")
        }

        paperButton.setOnClickListener {
            playGame("paper")
        }

        scissorsButton.setOnClickListener {
            playGame("scissors")
        }
    }

    private fun playGame(userChoice: String) {
        val computerChoice = getRandomChoice()
        setComputerChoiceImage(computerChoice)
        val result = determineWinner(userChoice, computerChoice)
        displayResult(result)
    }

    private fun getRandomChoice(): String {
        val choices = listOf("rock", "paper", "scissors")
        val random = Random.nextDouble()
        val index = (random * choices.size).toInt()
        return choices[index]
    }

    private fun setComputerChoiceImage(computerChoice: String) {
        val imageId = when (computerChoice) {
            "rock" -> R.drawable.rock
            "paper" -> R.drawable.paper
            "scissors" -> R.drawable.scissors
            else -> 0
        }
        computerChoiceImage.setImageResource(imageId)
    }

    private fun determineWinner(userChoice: String, computerChoice: String): String {
        return if (userChoice == computerChoice) {
            "It's a draw!"
        } else {
            val winConditions = mapOf(
                "rock" to "scissors",
                "paper" to "rock",
                "scissors" to "paper"
            )
            if (winConditions[userChoice] == computerChoice) {
                "You win!"
            } else {
                "You lose!"
            }
        }
    }

    private fun displayResult(result: String) {
        resultTextView.text = result
    }
}
