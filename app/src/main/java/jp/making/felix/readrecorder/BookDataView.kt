package jp.making.felix.readrecorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_book_data_view.*
import kotlinx.android.synthetic.main.activity_read_data_regist.*

class BookDataView : AppCompatActivity(), OnChartValueSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //本のデータを表示する画面の呼び出し
        setContentView(R.layout.activity_book_data_view)
        //メインアクティビティから渡したデータの取得
        val book = intent.getSerializableExtra("book")
        //lineChartの呼び出し
        val lineChart = findViewById<LineChart>(R.id.line_chart)
        //本のイメージビューの取得
        val image = findViewById<ImageView>(R.id.imageView)
        //本の名前表示のためのテキストビュー取得
        val bookName = findViewById<TextView>(R.id.bookName)
        //本のログ表示のためのテキストビュー取得
        val bookLog = findViewById<TextView>(R.id.bookLog)
        //Chartをコントロールするためのクラス
        val chartController = ChartController()

        /**
         * @TODO DBにパスをおいておき、それを呼び出して画像取得
         */
        image.setImageResource(R.drawable.kotlin)

        //戻るボタンの戻る実装
        backButton.setOnClickListener { view ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        /**
         * @TODO linechartのsetOnChartValueSelectedListenerをChartControllerに移植する。
         * @TODO (this@BookDataView)部分の対処
         */
        lineChart.setOnChartValueSelectedListener(this@BookDataView)

        if(book is Book){
            //LineChartのデータ設定と表示
            val pageData = mutableListOf<Int>()
            for(i in book.pages){
                pageData.add(i)
            }
            lineChart.data = chartController.setUpChart(lineChart,pageData.toTypedArray())
            //チャート表示画面のテキストビューに本の名前と最終更新履歴を表示する
            bookName.setText(book.name)
            bookLog.setText(book.lastLog)
        }

        addButton.setOnClickListener{view ->
            val intent = Intent(this,ReadDataRegist::class.java)
            intent.putExtra("book",book)
            val requestCode = 1000
            startActivityForResult(intent,requestCode)
        }
    }
    /**
     * グラフの座標が押された時によばれ、何ページ読んだかをトーストで表示します。
     * @param Entry 押された座標の情報を持つ
     */
    override fun onValueSelected(e: Entry, h: Highlight) {
        val context = baseContext
        Toast.makeText(context,"${e.y}ページ読んでいます",Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setContentView(R.layout.activity_book_data_view)
        //メインアクティビティから渡したデータの取得
        val book = intent.getSerializableExtra("book")
        //lineChartの呼び出し
        val lineChart = findViewById<LineChart>(R.id.line_chart)
        //本のイメージビューの取得
        val image = findViewById<ImageView>(R.id.imageView)
        //本の名前表示のためのテキストビュー取得
        val bookName = findViewById<TextView>(R.id.bookName)
        //本のログ表示のためのテキストビュー取得
        val bookLog = findViewById<TextView>(R.id.bookLog)
        //Chartをコントロールするためのクラス
        val chartController = ChartController()

        /**
         * @TODO DBにパスをおいておき、それを呼び出して画像取得
         */
        image.setImageResource(R.drawable.kotlin)

        //戻るボタンの戻る実装
        backButton.setOnClickListener { view ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        /**
         * @TODO linechartのsetOnChartValueSelectedListenerをChartControllerに移植する。
         * @TODO (this@BookDataView)部分の対処
         */
        lineChart.setOnChartValueSelectedListener(this@BookDataView)

        if(book is Book){
            //LineChartのデータ設定と表示
            val pageData = mutableListOf<Int>()
            for(i in book.pages){
                pageData.add(i)
            }
            lineChart.data = chartController.setUpChart(lineChart,pageData.toTypedArray())
            //チャート表示画面のテキストビューに本の名前と最終更新履歴を表示する
            bookName.setText(book.name)
            bookLog.setText(book.lastLog)
        }

        addButton.setOnClickListener{view ->
            val intent = Intent(this,ReadDataRegist::class.java)
            intent.putExtra("book",book)
            val requestCode = 1000
            startActivityForResult(intent,requestCode)
        }
    }
}
