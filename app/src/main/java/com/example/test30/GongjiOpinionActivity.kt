package com.example.test30

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.gongji_main.*
import kotlinx.android.synthetic.main.opinion_list_item.view.*
import kotlinx.android.synthetic.main.gongji_opinion_main.*
import kotlinx.android.synthetic.main.gongji_opinion_main.Sound_button
import kotlinx.android.synthetic.main.gongji_opinion_main.listView
import kotlinx.android.synthetic.main.gongji_opinion_main.back_button
import kotlinx.android.synthetic.main.gune_main.*
import java.text.SimpleDateFormat
import java.util.*

class GongjiOpinionActivity : AppCompatActivity() {
    private var tts: TextToSpeech? = null
    private fun initTextToSpeech(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            Toast.makeText(this,"SDK version is low", Toast.LENGTH_SHORT).show()
            return
        }
        tts = TextToSpeech(this){
            if(it == TextToSpeech.SUCCESS){
                val result = tts?.setLanguage(Locale.KOREAN)
                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(this,"Language not supported", Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(this,"TTS setting successed", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"TTS init failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun ttsSpeak(strTTS: String){
        tts?.speak(strTTS,TextToSpeech.QUEUE_ADD,null,null)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gongji_opinion_main)

        val items = mutableListOf<ListViewItem1>()
        val adapter = ListViewAdapter1(items)
        items.add(ListViewItem1("구종모","정말 좋은 정보입니다","2022년 02월 02일"))
        items.add(ListViewItem1("구종모","정말 좋은 정보입니다","2022년 02월 02일"))
        items.add(ListViewItem1("구종모","정말 좋은 정보입니다","2022년 02월 02일"))
        items.add(ListViewItem1("구종모","정말 좋은 정보입니다","2022년 02월 02일"))
        items.add(ListViewItem1("구종모","정말 좋은 정보입니다","2022년 02월 02일"))
        items.add(ListViewItem1("구종모","정말 좋은 정보입니다","2022년 02월 02일"))
        listView.adapter = adapter
        tts = TextToSpeech(this) { status ->
            if (status != TextToSpeech.ERROR) {
                // 언어를 선택한다.
                tts!!.language = Locale.KOREAN
            }
        }
        val secondintent =intent
        val dataFormat1 = SimpleDateFormat("yyyy.MM.dd")


        back_button.setOnClickListener({
            val intent = Intent(this, GongjiSubActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        })
    }

    override fun onBackPressed() {
        val intent = Intent(this, GongjiSubActivity::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
    }

}
data class ListViewItem1(val name: String, val text: String, val date: String)

class ListViewAdapter1(private val items: MutableList<ListViewItem1>): BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(position: Int): ListViewItem1 = items[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null)
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.opinion_list_item, parent, false)
        val item: ListViewItem1 = items[position]
        convertView!!.user_name.text = item.name
        convertView.user_text.text = item.text
        convertView.user_date.text = item.date

        return convertView
    }
}

