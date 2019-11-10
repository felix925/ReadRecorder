package jp.making.felix.readrecorder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_read_data_regist.*

class ReadDataRegist : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data_regist)
        val titleText = findViewById<TextView>(R.id.titleText)
        val book = intent.getSerializableExtra("book")
        titleText.text = "何ページまで読んだかを入力してください。"
        submitButton.setOnClickListener{view ->
            val intent = Intent(this,BookDataView::class.java)
            val pages = findViewById<EditText>(R.id.pageValue)
            val pageValue = pages.getText()
            if(book is Books){
                book.pages.add(Page(pageValue.toString().toInt()))
                intent.putExtra("book",book)
            }
            setResult(Activity.RESULT_OK,intent)
        }

    }
}
