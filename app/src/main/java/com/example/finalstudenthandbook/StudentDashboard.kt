package com.example.finalstudenthandbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class StudentDashboard : AppCompatActivity() {

    lateinit var mPDFView:PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_dashboard)

        mPDFView = findViewById(R.id.pdfView) as PDFView
        mPDFView.fromAsset("studenthandbook.pdf").load()
    }

    fun std_Logout(view: View) {
        val LoggingIn:LoggingIn= LoggingIn(this)
        LoggingIn.clearSaveCredentials()
        val mainIntent = Intent(this, Login::class.java)
        startActivity(mainIntent)
        finish()
    }



}