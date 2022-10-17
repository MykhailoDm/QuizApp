package com.calculator.quizapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.calculator.quizapp.constants.QuizConstants
import com.calculator.quizapp.constants.QuizConstants.USER_NAME
import com.calculator.quizapp.dto.QuestionDTO

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var userName: String? = null

    private var questionNumber: Int = 1
    private var questions: ArrayList<QuestionDTO>? = null
    private var selectedOption: Int = NO_OPTION_SELECTED
    private var correctAnswers: Int = 0

    private var progressBar: ProgressBar? = null
    private var progressCount: TextView? = null

    private var questionText: TextView? = null
    private var questionImage: ImageView? = null
    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null

    private var submitButton: Button? = null


    companion object {
        const val LAST_SUBMISSION = "FINISH"
        const val SUBMISSION = "SUBMIT"
        const val NEXT_QUESTION = "GO TO NEXT QUESTION"
        const val NO_OPTION_SELECTED = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(USER_NAME)

        progressBar = findViewById(R.id.question_progress_bar)
        progressCount = findViewById(R.id.question_progress_count)

        questionText = findViewById(R.id.question)
        questionImage = findViewById(R.id.question_image)

        optionOne = findViewById(R.id.option_one)
        optionTwo = findViewById(R.id.option_two)
        optionThree = findViewById(R.id.option_three)
        optionFour = findViewById(R.id.option_four)

        submitButton = findViewById(R.id.option_submit_btn)

        questions = QuizConstants.getQuestions()

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)

        submitButton?.setOnClickListener(this)

        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionsView()

        val question = questions!![getQuestionIndex(questionNumber)]

        questionImage?.setImageResource(question.image)

        progressBar?.progress = questionNumber
        progressCount?.text = "$questionNumber / ${progressBar?.max}"
        questionText?.text = question.question

        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour

        updateSubmitButtonText()
    }

    private fun updateSubmitButtonText() {
        if (questionNumber == questions!!.size) {
            submitButton?.text = LAST_SUBMISSION
        } else {
            submitButton?.text = SUBMISSION
        }
    }

    private fun selectOption(option: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()

        selectedOption = selectedOptionNumber
        option.setTextColor(ContextCompat.getColor(this, R.color.option_selected_text_color))
        option.setTypeface(option.typeface, Typeface.BOLD)
        option.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        optionOne?.let { options.add(it) }
        optionTwo?.let { options.add(it) }
        optionThree?.let { options.add(it) }
        optionFour?.let { options.add(it) }

        options.forEach { o ->
            o.setTextColor(ContextCompat.getColor(this, R.color.option_text_color))
            o.typeface = Typeface.DEFAULT
            o.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun getQuestionIndex(questionNumber: Int): Int {
        return questionNumber - 1
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option_one -> {
                optionOne?.let {
                    selectOption(it, 1)
                }
            }
            R.id.option_two -> {
                optionTwo?.let {
                    selectOption(it, 2)
                }
            }
            R.id.option_three -> {
                optionThree?.let {
                    selectOption(it, 3)
                }
            }
            R.id.option_four -> {
                optionFour?.let {
                    selectOption(it, 4)
                }
            }
            R.id.option_submit_btn -> {
                if (selectedOption == NO_OPTION_SELECTED) {
                    questionNumber++

                    when {
                        questionNumber <= questions!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(QuizConstants.USER_NAME, userName)
                            intent.putExtra(QuizConstants.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(QuizConstants.TOTAL_QUESTIONS, questions?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = questions?.get(getQuestionIndex(questionNumber))
                    if (question!!.correctAnswer != selectedOption) {
                        answer(selectedOption, R.drawable.wrong_option_border_bg)
                    } else {
                        correctAnswers++
                    }
                    answer(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (questionNumber == questions!!.size) {
                        submitButton?.text = LAST_SUBMISSION
                    } else {
                        submitButton?.text = NEXT_QUESTION
                    }

                    selectedOption = NO_OPTION_SELECTED
                }
            }
        }
    }

    private fun answer(answer: Int, drawableId: Int) {
        when (answer) {
            1 -> optionOne?.background = ContextCompat.getDrawable(this, drawableId)
            2 -> optionTwo?.background = ContextCompat.getDrawable(this, drawableId)
            3 -> optionThree?.background = ContextCompat.getDrawable(this, drawableId)
            4 -> optionFour?.background = ContextCompat.getDrawable(this, drawableId)
        }
    }
}