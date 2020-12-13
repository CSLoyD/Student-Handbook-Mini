package com.example.finalstudenthandbook

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.student_quizes.*

class Quizzes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_quizes)

        btnQuiz.setOnClickListener{

            //Assigning Values and Declaring
            val selectQuiz1: Int = btnRadio1.checkedRadioButtonId
            val Quiz1 = findViewById<RadioButton>(selectQuiz1)
            var Score1 = 0;
            val selectQuiz2: Int = btnRadio2!!.checkedRadioButtonId
            val Quiz2 = findViewById<RadioButton>(selectQuiz2)
            var Score2 = 0;
            val selectQuiz3: Int = btnRadio3!!.checkedRadioButtonId
            val Quiz3 = findViewById<RadioButton>(selectQuiz3)
            var Score3 = 0;
            val selectQuiz4: Int = btnRadio4!!.checkedRadioButtonId
            val Quiz4 = findViewById<RadioButton>(selectQuiz4)
            var Score4 = 0;
            val selectQuiz5: Int = btnRadio5!!.checkedRadioButtonId
            val Quiz5 = findViewById<RadioButton>(selectQuiz5)
            var Score5 = 0;
            val selectQuiz6: Int = btnRadio6!!.checkedRadioButtonId
            val Quiz6 = findViewById<RadioButton>(selectQuiz6)
            var Score6 = 0;
            val selectQuiz7: Int = btnRadio7!!.checkedRadioButtonId
            val Quiz7 = findViewById<RadioButton>(selectQuiz7)
            var Score7 = 0;
            val selectQuiz8: Int = btnRadio8!!.checkedRadioButtonId
            val Quiz8 = findViewById<RadioButton>(selectQuiz8)
            var Score8 = 0;
            val selectQuiz9: Int = btnRadio9!!.checkedRadioButtonId
            val Quiz9 = findViewById<RadioButton>(selectQuiz9)
            var Score9 = 0;
            val selectQuiz10: Int = btnRadio10!!.checkedRadioButtonId
            val Quiz10 = findViewById<RadioButton>(selectQuiz10)
            var Score10 = 0;
            var totalScore = 0;
            var msg = ""
            if(selectQuiz1 != -1 && selectQuiz2 != -1 && selectQuiz3 != -1 && selectQuiz4 != -1 && selectQuiz5 != -1 && selectQuiz6 != -1 && selectQuiz7 != -1 && selectQuiz8 != -1 && selectQuiz9 != -1 && selectQuiz10 != -1) {
                if (Quiz1.text == "Certificate of Registration") {
                    Score1 = 1;
                }
                if (Quiz2.text == "12") {
                    Score2 = 1;
                }
                if (Quiz3.text == "To get a higher grade") {
                    Score3 = 1;
                }
                if (Quiz4.text == "a. officially dropped") {
                    Score4 = 1;
                }
                if (Quiz5.text == "b. incomplete") {
                    Score5 = 1;
                }
                if (Quiz6.text == "a. no grade above 1.8 or 87% in all subjects in the current term.") {
                    Score6 = 1;
                }
                if (Quiz7.text == "b. compliance of the residency requirements") {
                    Score7 = 1;
                }
                if (Quiz8.text == "d. Teasurer's Office") {
                    Score8 = 1;
                }
                if (Quiz9.text == "a. 50% discount on tuition fees") {
                    Score9 = 1;
                }
                if (Quiz10.text == "d. student helper") {
                    Score10 = 1;
                }

                totalScore = Score1 + Score2 + Score3 + Score4 + Score5 + Score6 + Score7 + Score8 + Score9 + Score10
                if(totalScore == 10) {
                    msg = "Congratulations!"
                } else {
                    msg = "Try Again Next Time :)"
                }

                val builder = AlertDialog.Builder(this)
                builder.setMessage("Your score is: " + totalScore.toString() + " out of 10 \n\n" + msg )
                    .setCancelable(false)
                    .setNegativeButton(
                        "Close"
                    ) { dialog, id -> dialog.cancel() }
                val alert = builder.create()
                alert.show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please answer all questions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun gotoMain(view: View) {
        val mainIntent = Intent(this, StudentDashboard::class.java)
        startActivity(mainIntent)
        finish()
    }
}