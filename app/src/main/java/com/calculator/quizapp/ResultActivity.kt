package com.calculator.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.calculator.quizapp.constants.QuizConstants

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val nameText: TextView = findViewById(R.id.player_name)
        val scoreText: TextView = findViewById(R.id.player_score)
        val finishBtn: Button = findViewById(R.id.finish_btn)

        nameText.text = intent.getStringExtra(QuizConstants.USER_NAME)

        val totalQuestions = intent.getIntExtra(QuizConstants.TOTAL_QUESTIONS, 0)
        val correctQuestions = intent.getIntExtra(QuizConstants.CORRECT_ANSWERS, 0)
        scoreText.text = "Your Score is $correctQuestions out of $totalQuestions"

        finishBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}