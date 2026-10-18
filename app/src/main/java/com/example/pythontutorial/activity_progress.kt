package com.example.pythontutorial

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class activity_progress : AppCompatActivity() {

    lateinit var database: DatabaseHelper

    val allLessons = arrayOf("VARIABLE","INDEX", "DATA TYPE", "OPERATOR", "FUNCTION", "CLASS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_progress)
        findViewById<View>(R.id.main).applySystemBarsPadding()

        database = DatabaseHelper(this)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            finish()
        }

        val finishNames = ArrayList<String>()
        val finishScores = ArrayList<String>()
        var total = 0

        val c = database.seeData()
        while (c.moveToNext()) {
            val lesson = c.getString(1)
            val score = c.getInt(2)

            if (!finishNames.contains(lesson)) {
                finishNames.add(lesson)
                finishScores.add("$score Point")
                total += score
            }
        }
        c.close()

        val notFinishNames = ArrayList<String>()
        val notFinishScores = ArrayList<String>()
        for (lesson in allLessons) {
            if (!finishNames.contains(lesson)) {
                notFinishNames.add(lesson)
                notFinishScores.add("-")
            }
        }

        findViewById<ListView>(R.id.finish).adapter = makeAdapter(finishNames, finishScores)
        findViewById<ListView>(R.id.notFinish).adapter = makeAdapter(notFinishNames, notFinishScores)

        findViewById<TextView>(R.id.totalScore).text = "Total Score :\n\n\t\t\t\t$total Point"
    }

    fun makeAdapter(names: ArrayList<String>, scores: ArrayList<String>): ArrayAdapter<String> {
        return object : ArrayAdapter<String>(this, R.layout.finish_or_not, names) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val row = convertView ?: layoutInflater.inflate(R.layout.finish_or_not, parent, false)
                row.findViewById<TextView>(R.id.textView).text = names[position]
                row.findViewById<TextView>(R.id.textView2).text = scores[position]
                return row
            }
        }
    }
}