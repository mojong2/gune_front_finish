package com.example.test30

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.real_main.*

class MainActivity3 : AppCompatActivity() {
    var mBackWait : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.real_main_admin)

        singo_button.setOnLongClickListener({
            Toast.makeText(applicationContext,"구급차를 요청했습니다",Toast.LENGTH_SHORT).show()
            true
        })

        ge_button.setOnClickListener({
            ge_button.visibility = View.GONE
            gun_button.visibility = View.VISIBLE
            gong_button.visibility = View.VISIBLE
        })
        lt_button.setOnClickListener({
            val intent = Intent(this, GongjiInsertActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        gong_button.setOnClickListener({
            ge_button.visibility = View.VISIBLE
            gun_button.visibility = View.GONE
            gong_button.visibility = View.GONE
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        gun_button.setOnClickListener({
            ge_button.visibility = View.VISIBLE
            gun_button.visibility = View.GONE
            gong_button.visibility = View.GONE
            val intent = Intent(this, GuneActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
        setting_button.setOnClickListener({
            ge_button.visibility = View.VISIBLE
            gun_button.visibility = View.GONE
            gong_button.visibility = View.GONE
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit)
        })
    }

    var time3: Long = 0
    override fun onBackPressed() {
        val time1 = System.currentTimeMillis()
        val time2 = time1 - time3
        if (time2 in 0..2000) {
            ActivityCompat.finishAffinity(this)
            System.exit(0)
        }
        else {
            time3 = time1
            Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show()
        }
    }
}
