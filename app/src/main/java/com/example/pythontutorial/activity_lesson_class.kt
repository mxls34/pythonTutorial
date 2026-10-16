package com.example.pythontutorial

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class activity_lesson_class : AppCompatActivity(), View.OnClickListener {

    lateinit var showLesson: LinearLayout

    val choices = arrayOf("A. ตอนสร้างวัตถุ", "B. ตอนปิดโปรแกรม", "C. ตอนเรียก print", "D. ไม่เคยทำงาน")
    val correct = 0

    val lessonData: Array<Lesson> = arrayOf(
        Lesson("รู้จักคลาส (Class)", R.string.class_content),
        Lesson("ตัวอย่าง", R.string.class_content1),
        Lesson("QUIZ", R.string.class_quiz)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson_class)

        showLesson = findViewById(R.id.lesson)

        val backBtn = findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }

        for ((index, lesson) in lessonData.withIndex()){

            val row = layoutInflater.inflate(R.layout.lesson, showLesson, false)

            row.findViewById<TextView>(R.id.titleLesson).text = lesson.title
            row.findViewById<TextView>(R.id.contentLesson).setText(lesson.content)

            val radio_group = row.findViewById<RadioGroup>(R.id.choices)

            if(index == 1){
                row.setBackgroundResource(R.drawable.bg_code)

            }else if (index > 1){

                radio_group.isVisible = true

                for (choice in choices){

                    val radio = RadioButton(this)

                    radio.id = View.generateViewId()
                    radio.text = choice
                    radio.textSize = 16f

                    radio.setOnClickListener(this)
                    radio_group.addView(radio)
                }

            }

            row.findViewById<TextView>(R.id.titleLesson).text = lesson.title
            row.findViewById<TextView>(R.id.contentLesson).setText(lesson.content)

            showLesson.addView(row)
        }

    }

    override fun onClick(v: View?) {
        val group = v?.parent as RadioGroup
        val selected = group.indexOfChild(v)

        if (selected == correct) {
            Toast.makeText(this, "ถูกต้อง!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "ยังไม่ถูก", Toast.LENGTH_SHORT).show()
        }
    }
}