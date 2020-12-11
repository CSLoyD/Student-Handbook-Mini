package com.example.finalstudenthandbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class MainActivity : AppCompatActivity() {

    lateinit var mPDFView:PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPDFView = findViewById(R.id.pdfView) as PDFView
        mPDFView.fromAsset("studenthandbook.pdf").load()

    }
}