package com.example.test30

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.announce_insert_main.*

class ChatBotActivity : AppCompatActivity(){

    private fun initFirebase(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {

                firebaseToken.text = task.result
                Log.d("gi",firebaseToken.text.toString())
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.announce_insert_main)

    }
}