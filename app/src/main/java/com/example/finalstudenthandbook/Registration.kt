package com.example.finalstudenthandbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.student_profile.*

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btnReg.setOnClickListener{

            if( regUserName.text.trim().isNotEmpty() && regUserPass.text.trim().isNotEmpty() && regFirstName.text.trim().isNotEmpty() && regLastName.text.trim().isNotEmpty() ) {
                regProgress.visibility = View.VISIBLE

                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    //Start ng pagbasa ug pag modify sa database
                    //Himuan ng mga fields
                    val field = arrayOfNulls<String>(4)
                    field[0] = "rUserName"
                    field[1] = "rUserPass"
                    field[2] = "rFirstName"
                    field[3] = "rLastName"
                    //Maghimo ng mga data
                    val data = arrayOfNulls<String>(4)
                    data[0] = regUserName.text.toString()
                    data[1] = regUserPass.text.toString()
                    data[2] = regFirstName.text.toString()
                    data[3] = regLastName.text.toString()
                    val putData = PutData(
                        dbConnection().urldb + "StudentHandbook/signup.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            regProgress.visibility = View.GONE
                            val result = putData.result
                            val suc = "Sign Up Success"
                            if(result.equals(suc,true)) {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                val logIntent = Intent(this, Login::class.java)
                                startActivity(logIntent)
                                finish()
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            } else {
                Toast.makeText(applicationContext, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun goToLogin(view: View) {
        val mainIntent = Intent(this, Login::class.java)
        startActivity(mainIntent)
        finish()
    }


}