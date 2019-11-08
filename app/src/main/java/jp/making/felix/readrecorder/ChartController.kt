package jp.making.felix.readrecorder

import android.graphics.Color
import android.graphics.Typeface
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class ChartController{
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)

    fun setUpChart(lineChart: LineChart,page: Array<Int>):LineData{
        setupLineChart(lineChart)
        return(lineDataWithCount(page))
    }

    private fun setupLineChart(lineChart: LineChart){
        lineChart.apply {
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
    private fun lineDataWithCount(page:Array<Int>): LineData {
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