package com.example.finalstudenthandbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.student_dashboard.*

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

    fun std_updateprof(view: View) {
        val LoggingIn:LoggingIn= LoggingIn(this)
        val uid = LoggingIn.getUID("UID")

        sdProgress.visibility = View.VISIBLE
        val handler2 = Handler(Looper.getMainLooper())
        handler2.post {
            //Starting Write and Read data with URL
            //Creating array for parameters
            val fieldF = arrayOfNulls<String>(1)
            fieldF[0] = "UserF"
            //Creating array for data
            val dataF = arrayOfNulls<String>(1)
            dataF[0] = uid.toString()
            val putData = PutData(
                dbConnection().urldb + "StudentHandbook/fetchfname.php",
                "POST",
                fieldF,
                dataF
            )
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    val FirstName = putData.result

                    LoggingIn.saveFirstName("FirstName", FirstName)
                }
            }
            //End Write and Read data with URL
        }

        //Start ProgressBar first (Set visibility VISIBLE)
        val handler3 = Handler(Looper.getMainLooper())
        handler3.post {
            //Starting Write and Read data with URL
            //Creating array for parameters
            val fieldL = arrayOfNulls<String>(1)
            fieldL[0] = "UserL"
            //Creating array for data
            val dataL = arrayOfNulls<String>(1)
            dataL[0] = uid.toString()
            val putData = PutData(
                dbConnection().urldb + "StudentHandbook/fetchlname.php",
                "POST",
                fieldL,
                dataL
            )
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    val LastName = putData.result
                    LoggingIn.saveLastName("LastName", LastName)
                }
            }
            //End Write and Read data with URL
        }

        val mainIntent = Intent(this, UpdateProfile::class.java)
        startActivity(mainIntent)
        finish()
    }
    fun std_Quiz(view: View) {
        val mainIntent = Intent(this, Quizzes::class.java)
        startActivity(mainIntent)
        finish()
    }


}