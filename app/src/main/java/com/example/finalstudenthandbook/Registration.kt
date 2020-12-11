package com.example.finalstudenthandbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_dashboard)

        btnRegister.setOnClickListener{

            if( rUserName.text.trim().isNotEmpty() && rUserPass.text.trim().isNotEmpty() ) {
                rProgress.visibility = View.VISIBLE

                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    //Start ng pagbasa ug pag modify sa database
                    //Himuan ng mga fields
                    val field = arrayOfNulls<String>(2)
                    field[0] = "rUserName"
                    field[1] = "rUserPass"
                    //Maghimo ng mga data
                    val data = arrayOfNulls<String>(2)
                    data[0] = rUserName.text.toString()
                    data[1] = rUserPass.text.toString()
                    val putData = PutData(
                        dbConnection().urldb + "StudentHandbook/signup.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            rProgress.visibility = View.GONE
                            val result = putData.result
                            val suc = "Sign Up Success"
                            if(result.equals(suc,true)) {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                val logIntent = Intent(this@Registration, Login::class.java)
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


}