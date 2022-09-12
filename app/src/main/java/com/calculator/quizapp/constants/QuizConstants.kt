package com.calculator.quizapp.constants

import com.calculator.quizapp.R
import com.calculator.quizapp.dto.QuestionDTO

object QuizConstants {

    fun getQuestions(): ArrayList<QuestionDTO> {
        val questionList = ArrayList<QuestionDTO>()

        val  questionOne = QuestionDTO(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Australia",
            "Armenia", "Austria",
            1
        )
        questionList.add(questionOne)

        val  questionTwo = QuestionDTO(
            2, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Argentina", "Australia",
            "Armenia", "Austria",
            2
        )
        questionList.add(questionTwo)

        val  questionThree = QuestionDTO(
            3, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Argentina", "Australia",
            "Belgium", "Austria",
            3
        )
        questionList.add(questionThree)

        return questionList
    }

}