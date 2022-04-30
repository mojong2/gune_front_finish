package com.example.test30

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.gune_main.*
import kotlinx.android.synthetic.main.gune_main.back_button
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.custom_list_item.*
import kotlinx.android.synthetic.main.custom_list_item.view.*
import kotlinx.android.synthetic.main.gune_sub_main.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SubActivity2 : AppCompatActivity() {

    val items = mutableListOf<ListViewItem3>()
    val adapter = ListViewAdapter3(items)

    init {
        instance = this
    }

    companion object {
        lateinit var instance: SubActivity2
        fun SubActivity2Context(): Context {
            return instance.applicationContext
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        val currentTime: Long = System.currentTimeMillis()
        val anim_test =
            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_anim) // 글자 애니메이션

        setContentView(R.layout.gune_main)
        val cal = Calendar.getInstance()
        cal.time = Date()

        listView.adapter = adapter
        items.add(ListViewItem3("어쩔방구","2022년일까?",1))

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem3
            val intent = Intent(this, GuneSubActivity::class.java)

            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

        back_button.setOnClickListener({
            if (MySharedPreferences.getUserType(this).equals("0")) {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                ActivityCompat.finishAffinity(this)
                overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
                finish()
            } else if (MySharedPreferences.getUserType(this).equals("1")) {
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
                ActivityCompat.finishAffinity(this)
                overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
                finish()
            }
        })
        select_date.setOnClickListener({
            select_date.isClickable = false
            CVOn.visibility = View.VISIBLE
            input_gune.visibility = View.INVISIBLE
            listView.visibility = View.INVISIBLE
            CVOn.startAnimation(anim_test)
            var selected_date = ""
            CalendarView.setOnDateChangeListener { view, year, month, date ->
                if ((month + 1 < 10) && (date < 10)) {
                    selected_date = String.format("%d-0%d-0%d", year, month + 1, date)
                } else if ((month + 1 < 10) && (date >= 10)) {
                    selected_date = String.format("%d-0%d-%d", year, month + 1, date)
                } else if ((month + 1 >= 10) && (date < 10)) {
                    selected_date = String.format("%d-%d-0%d", year, month + 1, date)
                } else {
                    selected_date = String.format("%d-%d-%d", year, month + 1, date)
                }

            }
            choose_date.setOnClickListener({
                select_date.isClickable = true
                CVOn.visibility = View.INVISIBLE
                input_gune.visibility = View.VISIBLE
                listView.visibility = View.VISIBLE

            })

        })
        input_gune.setOnClickListener({
            val intent = Intent(this, GuneInsertActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            finish()
        })
    }

    override fun onBackPressed() {
        if (MySharedPreferences.getUserType(this).equals("0")) {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
            finish()
        } else if (MySharedPreferences.getUserType(this).equals("1")) {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
            finish()
        }
    }
}

data class ListViewItem3(val title: String,val date: String, val no: Int)

class ListViewAdapter3(private val items: MutableList<ListViewItem3>): BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(position: Int): ListViewItem3 = items[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null)
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.custom_list_item, parent, false)
        val item: ListViewItem3 = items[position]
        convertView!!.text_title.text = item.title
        convertView.text_date.text = item.date
        return convertView
    }
}