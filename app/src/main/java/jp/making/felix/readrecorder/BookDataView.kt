package jp.making.felix.readrecorder

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_book_data_view.*

class BookDataView : AppCompatActivity(), OnChartValueSelectedListener {
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)

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

        image.setImageResource(R.drawable.kotlin)

        //戻るボタンの戻る実装
        backButton.setOnClickListener { view ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        //LineChartのデータ設定と表示
        setupLineChart(lineChart)
        if(book is Books){
            lineChart.data = lineDataWithCount(book.pages)
            bookName.setText(book.name)
            bookLog.setText(book.lastLog)
        }

        addButton.setOnClickListener{view ->
            val intent = Intent(this,ReadDataRegist::class.java)
            intent.putExtra("book",book)
            startActivity(intent)
        }
    }
    //グラフ上の座標が押された場合に出力する。
    override fun onValueSelected(e: Entry, h: Highlight) {
        val context = baseContext
        Toast.makeText(context,"${e.y}ページ読んでいます",Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.")
    }
    private fun setupLineChart(lineChart:LineChart){
        lineChart.apply {
            setOnChartValueSelectedListener(this@BookDataView)
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = false
            isScaleXEnabled = true
            setPinchZoom(false)
            setDrawGridBackground(false)

            //データラベルの表示
            legend.apply {
                form = Legend.LegendForm.LINE
                typeface = mTypeface
                textSize = 11f
                textColor = Color.BLACK
                verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }

            //y軸右側の表示
            axisRight.isEnabled = false

            //X軸表示
            xAxis.apply {
                typeface = mTypeface
                setDrawLabels(false)
                setDrawGridLines(true)
            }

            //y軸左側の表示
            axisLeft.apply {
                typeface = mTypeface
                textColor = Color.BLACK
                setDrawGridLines(true)
            }
        }
    }
    private fun lineDataWithCount(page:Array<Int>):LineData {
        val values = mutableListOf<Entry>()
        var j = 0
        for (i in page) {
            val value = i.toFloat()
            values.add(Entry(j.toFloat(), value))
            j++
        }
        // create a dataset and give it a type
        val yVals = LineDataSet(values, "読書ページ推移").apply {
            axisDependency =  YAxis.AxisDependency.LEFT
            color = Color.BLACK
            highLightColor = Color.RED
            setDrawCircles(true)
            setDrawCircleHole(false)
            setDrawValues(true)
            lineWidth = 2f
        }
        val data = LineData(yVals)
        data.setValueTextColor(Color.BLACK)
        data.setValueTextSize(9f)
        return data
    }
}
