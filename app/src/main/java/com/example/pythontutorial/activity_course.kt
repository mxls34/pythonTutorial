package com.example.pythontutorial

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_course : AppCompatActivity() , AdapterView.OnItemClickListener{

    lateinit var gridCourse: GridView

    val listCourse: Array<Course> = arrayOf(
        Course(R.drawable.variable, "Variable Lesson", "ใน course นี้คุณจะได้เรียนรู้พื้นฐานการใช้งานตัวแปรและการจัดการข้อมูลประเภทต่างๆ"),
        Course(R.drawable.index, "Index Lesson", "ใน course นี้คุณจะได้เรียนรู้หลักการทำงานพื้นฐานของ Index ในระบบฐานข้อมูล เพื่อช่วยเพิ่มประสิทธิภาพและความเร็วในการค้นหาข้อมูล"),
        Course(R.drawable.datatype, "Data Type Lesson", "ใน course นี้คุณจะได้เรียนรู้และเข้าใจพื้นฐานเกี่ยวกับการจัดการประเภทหรือชนิดของข้อมูลต่างๆ"),
        Course(R.drawable.operator, "Operator Lesson", "ใน course นี้คุณจะได้เรียนรู้และเข้าใจเกี่ยวกับการใช้งานตัวดำเนินการ (Operators) ประเภทต่างๆ เช่น ทางคณิตศาสตร์ เปรียบเทียบ และตรรกะ เพื่อใช้ประมวลผลข้อมูลในการเขียนโปรแกรม"),
        Course(R.drawable.function, "Function Lesson", "ใน course นี้คุณจะได้เรียนรู้การสร้างและการใช้งานฟังก์ชันเพื่อจัดระเบียบชุดคำสั่งให้สามารถนำกลับมา reuse ซ้ำได้อย่างมีประสิทธิภาพ"),
        Course(R.drawable.course_class, "Class Lesson", "ใน course นี้คุณจะได้เรียนรู้หลักการเขียนโปรแกรมเชิงวัตถุ (Object-Oriented Programming) เพื่อสร้างแม่แบบ (Blueprint) สำหรับกำหนดคุณสมบัติและพฤติกรรมของออบเจ็กต์ภายในโปรแกรม"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_course)

        val adapter: AdapterCourse = AdapterCourse(this, listCourse)
        gridCourse = findViewById(R.id.gridCourse)
        gridCourse.adapter = adapter
        gridCourse.onItemClickListener

        val backBtn: ImageButton = findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }


    }

    override fun onItemClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        Toast.makeText(this, "bababa", Toast.LENGTH_SHORT).show()
    }
}