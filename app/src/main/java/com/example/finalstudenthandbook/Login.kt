package com.example.finalstudenthandbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val LoggingIn: LoggingIn = LoggingIn(this)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val lUsername = findViewById<TextView>(R.id.loginUsername).text.toString()

        fun checkSession() {
            if(LoggingIn.getUserType("userType") == "student"){
                val logIntent = Intent(this, StudentDashboard::class.java)
                startActivity(logIntent)
                finish()
            }
        }

        @Override
        fun onStart() {
            super.onStart()
            checkSession()
        }
        onStart()

        btnLogin.setOnClickListener{
            if(loginUsername.text.trim().isNotEmpty() && loginPass.text.trim().isNotEmpty()) {
                lProgress.visibility = View.VISIBLE
                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    //Start ng pagbasa ug pag modify sa database
                    //Himuan ng mga fields
                    val field = arrayOfNulls<String>(2)
                    field[0] = "loginUsername"
                    field[1] = "loginPass"
                    //Magassign ng mga data
                    val data = arrayOfNulls<String>(2)
                    data[0] = loginUsername.text.toString()
                    data[1] = loginPass.text.toString()
                    val putData = PutData(
                        dbConnection().urldb + "StudentHandbook/login.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            lProgress.visibility = View.GONE
                            val result = putData.result
                            val suc = "Login Successfully"
                            if (result.equals(suc, true)) {

                                val handler = Handler(Looper.getMainLooper())
                                handler.post {
                                    //Starting Write and Read data with URL
                                    //Creating array for parameters
                                    val fielduserType = arrayOfNulls<String>(1)
                                    fielduserType[0] = "loginUser"
                                    //Creating array for data
                                    val datauserType = arrayOfNulls<String>(1)
                                    datauserType[0] = loginUsername.text.toString()
                                    val putData = PutData(
                                        dbConnection().urldb + "StudentHandbook/loginAuthenticate.php",
                                        "POST",
                                        field,
                                        data
                                    )
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            val userType = putData.result

                                            LoggingIn.saveUsername("lUsername", loginUsername.text.toString())
                                            LoggingIn.saveUserType("userType", userType)
                                            LoggingIn.saveLogin("isLogin", true)

                                            //Start ProgressBar first (Set visibility VISIBLE)
                                            val handler2 = Handler(Looper.getMainLooper())
                                            handler2.post {
                                                //Starting Write and Read data with URL
                                                //Creating array for parameters
                                                val fieldF = arrayOfNulls<String>(1)
                                                fieldF[0] = "UserF"
                                                //Creating array for data
                                                val dataF = arrayOfNulls<String>(1)
                                                dataF[0] = loginUsername.text.toString()
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
                                                dataL[0] = loginUsername.text.toString()
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

                                            Toast.makeText(
                                                applicationContext,
                                                suc,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            val mainIntent = Intent(
                                                this,
                                                StudentDashboard::class.java
                                            )
                                            startActivity(mainIntent)
                                            finish()
                                        }
                                    }
                                }

                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    }
                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please fill out all fields!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    fun goToRegister(view: View) {
        val mainIntent = Intent(this, Registration::class.java)
        startActivity(mainIntent)
        finish()
    }
}