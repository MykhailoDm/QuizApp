package com.calculator.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.calculator.quizapp.constants.QuizConstants

class QuizQuestionsActivity : AppCompatActivity() {

    companion object {
        const val TAG = "QuizQuestionsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val questions = QuizConstants.getQuestions()

        Log.d(TAG, "onCreate: size is ${questions.size}")
    }
}