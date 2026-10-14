package com.example.pythontutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class AdapterCourse(
    context: Context,
    val imgCourse: Array<Course>
): ArrayAdapter<Course>(context, R.layout.grid_course, imgCourse) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.grid_course, parent, false)

        val title: TextView = row.findViewById(R.id.courseTitle)
        val content : TextView = row.findViewById(R.id.courseContent)
        val image: ImageView = row.findViewById(R.id.courseImg)

        title.text = imgCourse[position].title
        content.text = imgCourse[position].content

        image.setImageResource(imgCourse[position].imgId)

        return row
    }
}