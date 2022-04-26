package com.example.test30

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.setting_main.*

class AnounceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.anounce_main)
        back_button.setOnClickListener({

            if(MySharedPreferences.getUserType(this).equals("0")) {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                ActivityCompat.finishAffinity(this)
                overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
                finish()
            }
            else if(MySharedPreferences.getUserType(this).equals("1")) {
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
                ActivityCompat.finishAffinity(this)
                overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
                finish()
            }

        })
    }
    override fun onBackPressed() {
        if(MySharedPreferences.getUserType(this).equals("0")) {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
            finish()
        }
        else if(MySharedPreferences.getUserType(this).equals("1")) {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
            finish()
        }
    }

}
