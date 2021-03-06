package com.example.test30

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.announce_insert_main.*
import kotlinx.android.synthetic.main.find_pw_main.*
import kotlinx.android.synthetic.main.login_main.*
import kotlinx.android.synthetic.main.login_main.back_button
import kotlinx.android.synthetic.main.login_main.text1
import okhttp3.internal.Internal.instance
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }
    companion object {
        lateinit var instance: MainActivity
        fun MainActivityContext(): Context {
            return instance.applicationContext
        }
    }

    var userId = ""
    var userPw = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_main)

        if(intent.hasExtra("name") && intent.hasExtra("pw")){
            text1.setText(intent.getStringExtra("name"))
            text2.setText(intent.getStringExtra("pw"))
        }
        loginbutton.setOnClickListener({

            if(text1.getText().toString().equals("") || text1.getText().toString() == null){
                error_text_login.text = "아이디를 입력해주세요."
            }
            else if(text1.getText().length < 8){
                error_text_login.text = "8자 이상 입력해주세요."
            }
            else if(text2.getText().toString().equals("") || text2.getText().toString() == null){
                error_text_login.text = "비밀번호를 입력해주세요."
            }
            else if(text2.getText().length < 8){
                error_text_login.text = "8자 이상 입력해주세요."
            }
            else {
                userId = text1.getText().toString()
                userPw = text2.getText().toString()
                loginCheck(userId, userPw)
            }

//            val intent = Intent(this, MainActivity2::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        back_button.setOnClickListener({
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
        create_id_button.setOnClickListener({
            val intent = Intent(this, CreateIdActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        forget_pwbutton.setOnClickListener({
            val intent = Intent(this, FindPwActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
    }
    override fun onBackPressed() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }

    private fun loginCheck(ID: String, PW: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://sejongcountry.dothome.co.kr/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginInterface::class.java)
        val call: Call<String> = service.login(ID, PW)
        call.enqueue(object: Callback<String> {

            override fun onResponse(call: Call<String>, response: retrofit2.Response<String>) {
                if(response.isSuccessful && response.body() != null) {
                    var result = response.body().toString()
                    Log.d("Reg", "onResponse Success : " + response.toString())
                    Log.d("Reg", "onResponse Success : " + result)

                    val info = JSONObject(result)
                    val status = info.getString("status")

                    if(status.equals("true")) {

                        val TYPE = info.getString("type")

                        MySharedPreferences.setUserId(MainActivity.MainActivityContext(), ID)
                        MySharedPreferences.setUserPw(MainActivity.MainActivityContext(), PW)
                        MySharedPreferences.setUserType(MainActivity.MainActivityContext(), TYPE)
                        Toast.makeText(MainActivity.MainActivityContext(), "${MySharedPreferences.getUserId(MainActivity.MainActivityContext())}님 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                        if(TYPE.equals("1")) {
                            var intent = Intent(MainActivity.MainActivityContext(), MainActivity3::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
                            finish()
                        }
                        else if(TYPE.equals("0")) {
                            var intent = Intent(MainActivity.MainActivityContext(), MainActivity2::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
                            finish()
                        }

                    }
                    else if(status.equals("NoId")) {
                        error_text_login.text = "아이디가 존재하지 않습니다."
                    }
                    else if(status.equals("NoPw")) {
                        error_text_login.text = "비밀번호가 일치하지 않습니다."
                    }
                }
                else {
                    Log.d("Reg", "onResponse Failed")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Reg", "error : " + t.message.toString())
            }

        })

    }

}
