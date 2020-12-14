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
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.student_profile.*
import kotlinx.android.synthetic.main.student_profile.rFirstName
import kotlinx.android.synthetic.main.student_profile.rLastName
import kotlinx.android.synthetic.main.student_profile.rUserName
import kotlinx.android.synthetic.main.student_profile.rUserPass

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
        val uid = LoggingIn.getUID("UID")

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
            profileProgress.visibility = View.VISIBLE
            val LoggingIn: LoggingIn = LoggingIn(this)
            val uid = LoggingIn.getUID("UID")
            if( rUserName.text.trim().isNotEmpty() && rUserPass.text.trim().isNotEmpty() && rFirstName.text.trim().isNotEmpty() && rLastName.text.trim().isNotEmpty() ) {
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(5)
                    field[0] = "rUserID"
                    field[1] = "rUserName"
                    field[2] = "rUserPass"
                    field[3] = "rFirstName"
                    field[4] = "rLastName"
                    //Creating array for data
                    val data = arrayOfNulls<String>(5)
                    data[0] = uid.toString()
                    data[1] = rUserName.text.toString()
                    data[2] = rUserPass.text.toString()
                    data[3] = rFirstName.text.toString()
                    data[4] = rLastName.text.toString()
                    val putData = PutData(
                        dbConnection().urldb + "StudentHandbook/updateProfile.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            profileProgress.visibility = View.GONE
                            val result = putData.result
                            val suc = "Profile Updated!"
                            if (result.equals(suc, true)) {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                LoggingIn.saveUsername("lUsername", rUserName.text.toString())
                                LoggingIn.saveFirstName("LastName", rFirstName.text.toString())
                                LoggingIn.saveLastName("LastName", rLastName.text.toString())


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
            } else {
                Toast.makeText(applicationContext, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun backMain(view: View) {

        val mainIntent = Intent(this, StudentDashboard::class.java)
        startActivity(mainIntent)
        finish()
    }
}