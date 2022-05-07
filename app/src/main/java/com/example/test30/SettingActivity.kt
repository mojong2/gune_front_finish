package com.example.test30

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.create_id_main.*
import kotlinx.android.synthetic.main.setting_main.*
import kotlinx.android.synthetic.main.setting_main.back_button
import okhttp3.internal.Version

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.setting_main)

        var pw = "12341234"
        back_button.setOnClickListener({
            //            val intent = Intent(this, MainActivity2::class.java)
//            startActivity(intent)
//            ActivityCompat.finishAffinity(this)
//            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)

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

        logout_button.setOnClickListener {
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            MySharedPreferences.clearUser(this)
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }
        Version_button.setOnClickListener({
            if(Current_Version_button.visibility == View.VISIBLE) Current_Version_button.visibility = View.GONE
            else Current_Version_button.visibility = View.VISIBLE
        })
        delete_id.setOnClickListener({
            if(password_input.visibility == View.VISIBLE) password_input.visibility = View.GONE
            else password_input.visibility = View.VISIBLE
            if(password_input.getText().toString().equals(pw)){
                really_delete.visibility == View.VISIBLE
            }
            else really_delete.visibility == View.GONE
        })
        really_delete.setOnClickListener({
            val intent = Intent(this, StartActivity::class.java)
            var dialog = AlertDialog.Builder(this)
            var dialog1 = AlertDialog.Builder(this)
            dialog.setTitle("계정 탈퇴")
            dialog.setMessage("계정을 탈퇴하시겠습니까?")
            var dialog_listener = object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which){
                        DialogInterface.BUTTON_POSITIVE -> { // 삭제 버튼이 눌렸음
                            dialog1.setTitle("계정 탈퇴 완료")
                            dialog1.setMessage("계정을 탈퇴했습니다!")
                            var dialog_listener = object: DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    when(which){
                                        DialogInterface.BUTTON_POSITIVE -> {

                                        }
                                    }
                                }
                            }
                            dialog1.setPositiveButton("확인",dialog_listener)
                            dialog1.show()
                        }
                    }
                }
            }
            dialog.setPositiveButton("탈퇴하기",dialog_listener)
            dialog.setNegativeButton("취소",dialog_listener)
            dialog.show()

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
