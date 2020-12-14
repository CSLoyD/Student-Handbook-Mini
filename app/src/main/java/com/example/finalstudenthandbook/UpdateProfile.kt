package com.example.finalstudenthandbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.student_profile.*

class UpdateProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_profile)

        //Getting Values
        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
        val LoggingIn: LoggingIn = LoggingIn(this)
        val fname = LoggingIn.getFirstName("FirstName")
        val lname = LoggingIn.getLastName("LastName")
        val username = LoggingIn.getUsername("lUsername")

        if (fname != null) {
            rFirstName.text = fname.toEditable()
        }
        if (lname != null) {
            rLastName.text = lname.toEditable()
        }
        if (username != null) {
            rUserName.text = username.toEditable()
        }

        adProfUpdate.setOnClickListener {
            //Start ProgressBar first (Set visibility VISIBLE)
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                //Starting Write and Read data with URL
                //Creating array for parameters
                val field = arrayOfNulls<String>(4)
                field[0] = "rUserName"
                field[1] = "rUserPass"
                field[2] = "rFirstName"
                field[3] = "rLastName"
                //Creating array for data
                val data = arrayOfNulls<String>(4)
                data[0] = rFirstName.text.toString()
                data[1] = rLastName.text.toString()
                data[2] = rUserName.text.toString()
                data[3] = rUserPass.text.toString()
                val putData = PutData(
                    dbConnection().urldb + "StudentHandbook/updateProfile.php",
                    "POST",
                    field,
                    data
                )
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        val result = putData.result
                        val suc = "Profile Updated!"
                        if (result.equals(suc, true)) {
                            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()

                            val mainIntent = Intent(this, StudentDashboard::class.java)
                            startActivity(mainIntent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                //End Write and Read data with URL
            }
        }
    }

    fun backMain(view: View) {

        val mainIntent = Intent(this, StudentDashboard::class.java)
        startActivity(mainIntent)
        finish()
    }
}