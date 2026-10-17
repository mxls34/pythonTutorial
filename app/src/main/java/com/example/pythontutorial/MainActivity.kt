package com.example.pythontutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigate: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigate = findViewById(R.id.navigation)
        bottomNavigate.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.action_home) {
                true
            } else if (item.itemId == R.id.action_game) {
                startActivity(Intent(this, activity_game::class.java))
                true
            } else if (item.itemId == R.id.action_course) {
                startActivity(Intent(this, activity_course::class.java))
                true
            } else if (item.itemId == R.id.action_profile) {
                startActivity(Intent(this, activity_profile::class.java))
                true
            } else if(item.itemId == R.id.action_progress) {
                startActivity(Intent(this, activity_progress::class.java))
                true
            }else{
                false
            }
        }

    }


}