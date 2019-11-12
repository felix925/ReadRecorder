package jp.making.felix.readrecorder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_read_data_regist.*

class ReadDataRegist : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data_regist)
        val titleText = findViewById<TextView>(R.id.titleText)
        val intent =intent
        val bookId = intent.getStringExtra("bookId")
        titleText.text = "何ページまで読んだかを入力してください。"
        val rcon = RealmController()
        val bookData = rcon.findData(bookId)
        submitButton.setOnClickListener{view ->
            val intentSub = Intent(this,BookDataView::class.java)
            val pages = findViewById<EditText>(R.id.pageValue)
            val pageValue = pages.getText()
            bookId?.apply {
                rcon.updateData(bookId, pageValue.toString().toInt())
            }
            intentSub.putExtra("bookId",bookId)
            startActivity(intentSub)
        }

    }
}
