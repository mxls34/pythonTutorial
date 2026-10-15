package com.example.pythontutorial

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class activity_datatype : AppCompatActivity() {

    lateinit var showLesson: LinearLayout

    val lessonData: Array<Lesson> = arrayOf(
        Lesson("รู้จักชนิดของข้อมูล", R.string.datatype_content),
        Lesson("ตัวอย่าง", R.string.datatype_content1),
        Lesson("การจัดการข้อมูล", R.string.datatype_content2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datatype)

        showLesson = findViewById(R.id.lesson)

        val backBtn = findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }

        for ((index,lesson) in lessonData.withIndex()){
            val row = layoutInflater.inflate(R.layout.lesson, showLesson, false)

            if (index == 1){
                row.setBackgroundResource(R.drawable.bg_code)
            }

            row.findViewById<TextView>(R.id.titleLesson).text = lesson.title
            row.findViewById<TextView>(R.id.contentLesson).setText(lesson.content)

            showLesson.addView(row)
        }

    }
}