package com.example.finalstudenthandbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class UpdateProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_profile)

    }

    fun backMain(view: View) {
        val mainIntent = Intent(this, StudentDashboard::class.java)
        startActivity(mainIntent)
        finish()
    }
}