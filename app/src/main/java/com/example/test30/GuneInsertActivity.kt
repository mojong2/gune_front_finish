package com.example.test30

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.ERROR
import android.util.TypedValue
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.gune_write_main.*
import kotlinx.android.synthetic.main.gune_main.*
import kotlinx.android.synthetic.main.gune_write_main.*
import kotlinx.android.synthetic.main.gune_write_main.back_button
import java.util.*

class GuneInsertActivity : AppCompatActivity() {
    private var tts: TextToSpeech? = null
    private val requiredPermissions = arrayOf(
        android.Manifest.permission.RECORD_AUDIO
    )
    private fun initTextToSpeech(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            Toast.makeText(this,"SDK version is low",Toast.LENGTH_SHORT).show()
            return
        }
        tts = TextToSpeech(this){
            if(it == TextToSpeech.SUCCESS){
                val result = tts?.setLanguage(Locale.KOREAN)
                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(this,"Language not supported",Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(this,"TTS setting successed",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"TTS init failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun ttsSpeak(strTTS: String){
        tts?.speak(strTTS,TextToSpeech.QUEUE_ADD,null,null)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.gune_write_main)



        tts = TextToSpeech(this) { status ->
            if (status != ERROR) {
                // 언어를 선택한다.
                tts!!.language = Locale.KOREAN
            }
        }
        back_button.setOnClickListener({
            val intent = Intent(this, SubActivity2::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
            finish()
        })
        size_up_button.setOnClickListener({
            if(size_text.text == "작게"){
                size_text.text = "중간"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
            }
            else if(size_text.text == "중간"){
                size_text.text = "크게"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50.0f)
            }
        })
        size_small_button.setOnClickListener({
            if(size_text.text == "중간"){
                size_text.text = "작게"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30.0f)
            }
            else if(size_text.text == "크게"){
                size_text.text = "중간"
                title_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
                contents_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.0f)
            }
        })
        Sound_button.setOnClickListener({
            ttsSpeak(contents_text.getText().toString())
        })
    }
    override fun onBackPressed() {
        val intent = Intent(this, SubActivity2::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        finish()
    }

}
