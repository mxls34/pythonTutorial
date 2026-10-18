package com.example.pythontutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class activity_variable : AppCompatActivity() {

    lateinit var showLesson: LinearLayout
    lateinit var database: DatabaseHelper
    var quizGroup: RadioGroup? = null

    val choices = arrayOf("A. 15 = age", "B. age = 15", "C. age == 15", "D. age : 15")
    val correct = 1

    val lessonData: Array<Lesson> = arrayOf(
        Lesson("รู้จักตัวแปร", R.string.variable_content),
        Lesson("ตัวอย่าง", R.string.variable_content1),
        Lesson("QUIZ", R.string.variable_quiz)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_variable)
        findViewById<View>(R.id.main).applySystemBarsPadding()

        showLesson = findViewById(R.id.lesson)
        database = DatabaseHelper(this)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            finish()
        }

        for ((index, lesson) in lessonData.withIndex()) {
            val row = layoutInflater.inflate(R.layout.lesson, showLesson, false)

            row.findViewById<TextView>(R.id.titleLesson).text = lesson.title
            row.findViewById<TextView>(R.id.contentLesson).setText(lesson.content)

            val radioGroup = row.findViewById<RadioGroup>(R.id.choices)

            if (index == 1) {
                row.setBackgroundResource(R.drawable.bg_code)
            } else if (index > 1) {
                radioGroup.isVisible = true
                for (choice in choices) {
                    val radio = RadioButton(this)
                    radio.id = View.generateViewId()
                    radio.text = choice
                    radio.textSize = 16f
                    radioGroup.addView(radio)
                }
                quizGroup = radioGroup
            }

            showLesson.addView(row)
        }

        findViewById<Button>(R.id.quiz).setOnClickListener {
            val group = quizGroup ?: return@setOnClickListener
            val checkedId = group.checkedRadioButtonId

            if (checkedId == -1) {
                Toast.makeText(this, "กรุณาเลือกคำตอบก่อน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selected = group.indexOfChild(group.findViewById(checkedId))
            val score = if (selected == correct) 2 else 0
            database.insertData("VARIABLE", score)

            val msg = if (selected == correct) "ถูกต้อง! +2 คะแนน" else "ยังไม่ถูก ได้ 0 คะแนน"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}