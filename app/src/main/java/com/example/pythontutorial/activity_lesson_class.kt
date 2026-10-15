package com.example.pythontutorial

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_lesson_class : AppCompatActivity() {

    lateinit var showLesson: LinearLayout

    val lessonData: Array<Lesson> = arrayOf(
        Lesson("รู้จักตัวแปร", R.string.variable_content),
        Lesson("ชนิดข้อมูลพื้นฐาน,", R.string.variable_content),
        Lesson("การจัดการข้อมูล", R.string.variable_content)
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

        for (lesson in lessonData){

            val row = layoutInflater.inflate(R.layout.lesson, showLesson, false)

            row.findViewById<TextView>(R.id.titleLesson).text = lesson.title
            row.findViewById<TextView>(R.id.contentLesson).setText(lesson.content)

            showLesson.addView(row)
        }

    }
}