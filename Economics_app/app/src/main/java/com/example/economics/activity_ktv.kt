package com.example.economics

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_kpv.*
import kotlinx.android.synthetic.main.activity_kpv.scrollView1
import kotlinx.android.synthetic.main.activity_kpv.textView2
import kotlinx.android.synthetic.main.activity_kpv.textView6
import kotlinx.android.synthetic.main.activity_ktv.*
import java.lang.annotation.ElementType
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_kpv.textView5 as textView51
import kotlinx.android.synthetic.main.activity_ktv.imageView2 as imageView21

class activity_ktv : AppCompatActivity() {

    public val px_py = (0.5).toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        val arguments = intent.extras
        val flag_kpv = arguments?.getInt("flag")
        if(flag_kpv == 0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            setTheme(R.style.AppTheme)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.AppTheme2)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktv)
        setSupportActionBar(findViewById(R.id.toolbar2))

        val linechart1 = findViewById(R.id.line_chart1) as LineChart

        val y_kpv_1 = 20
        val x_kpv_1 = 20

        val ocx_1 = y_kpv_1.toFloat() / x_kpv_1.toFloat()

        val kpv_1 = ArrayList<Entry>()
        kpv_1.add(Entry(0f, y_kpv_1.toInt() + 0f))
        kpv_1.add(Entry(x_kpv_1.toInt() + 0f, 0f))

        val kpv_1_gr = LineDataSet(kpv_1, "КПВ_1")
        kpv_1_gr.setDrawValues(true)
        kpv_1_gr.lineWidth = 2f
        kpv_1_gr.setColor(Color.rgb(140, 1, 0))
        kpv_1_gr.setCircleColor(Color.rgb(140, 1, 0))

        linechart1.data = LineData(kpv_1_gr)

        val linechart2 = findViewById(R.id.line_chart2) as LineChart

        val y_kpv_2 = 10
        val x_kpv_2 = 20

        val ocx_2 = y_kpv_2.toFloat() / x_kpv_2.toFloat()

        val kpv_2 = ArrayList<Entry>()
        kpv_2.add(Entry(0f, y_kpv_2.toInt() + 0f))
        kpv_2.add(Entry(x_kpv_2.toInt() + 0f, 0f))

        val kpv_2_gr = LineDataSet(kpv_2, "КПВ_2")
        kpv_2_gr.setDrawValues(true)
        kpv_2_gr.lineWidth = 2f
        kpv_2_gr.setColor(Color.rgb(8, 117, 8))
        kpv_2_gr.setCircleColor(Color.rgb(8, 117, 8))
        linechart2.data = LineData(kpv_2_gr)

        val linechart3 = findViewById(R.id.line_chart3) as LineChart

        val ktv_1 = ArrayList<Entry>()
        val thrid_dot = (y_kpv_1.toFloat() - x_kpv_2.toFloat() * px_py) * ocx_1 + x_kpv_2.toFloat()
        ktv_1.add(Entry(0f, y_kpv_1 + 0f))
        ktv_1.add(Entry(x_kpv_2 + 0f, x_kpv_2 * px_py + 0f))
        ktv_1.add(Entry(thrid_dot+0f, 0f))

        val ktv_1_gr = LineDataSet(ktv_1, "КТВ_1")
        ktv_1_gr.setDrawValues(true)
        ktv_1_gr.lineWidth = 2f
        ktv_1_gr.setColor(Color.rgb(0, 0, 0))
        ktv_1_gr.setCircleColor(Color.rgb(0, 0, 0))

        linechart3.data = LineData(ktv_1_gr)

        val linechart4 = findViewById(R.id.line_chart4) as LineChart

        val ktv_2 = ArrayList<Entry>()

        ktv_2.add(Entry(0f, x_kpv_2*px_py + 0f))
        ktv_2.add(Entry(x_kpv_2 + 0f,  0f))

        val ktv_2_gr = LineDataSet(ktv_2, "КТВ_2")
        ktv_2_gr.setDrawValues(true)
        ktv_2_gr.lineWidth = 2f
        ktv_2_gr.setColor(Color.rgb(116, 5, 168))
        ktv_2_gr.setCircleColor(Color.rgb(116, 5, 168))

        linechart4.data = LineData(ktv_2_gr)

        val seek1 = findViewById<SeekBar>(R.id.seekBar_ktv_1)
        seek1?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek1: SeekBar,
                                           progress: Int, fromUser: Boolean) {


                val px_py = ((seek1.progress.toFloat() + 100)/200)

                val test = px_py

                ktv_1.clear()
                ktv_2.clear()

                val thrid_dot = (y_kpv_1.toFloat() - x_kpv_2.toFloat() * test) * ocx_1 + x_kpv_2.toFloat()

                ktv_1.add(Entry(0f, y_kpv_1 + 0f))
                ktv_1.add(Entry(x_kpv_2 + 0f, x_kpv_2 * test + 0f))
                ktv_1.add(Entry(thrid_dot+0f, 0f))

                ktv_2.add(Entry(0f, x_kpv_2*px_py + 0f))
                ktv_2.add(Entry(x_kpv_2 + 0f,  0f))

                linechart3.data = LineData(ktv_1_gr)
                linechart4.data = LineData(ktv_2_gr)

            }

            override fun onStartTrackingTouch(seek1: SeekBar) {

            }

            override fun onStopTrackingTouch(seek1: SeekBar) {
                // write custom code for progress is stopped
                Toast.makeText(applicationContext,
                    "Progress is: " + seek1.progress + "%",
                    Toast.LENGTH_SHORT).show()

            }

        })

    }
    fun menu_onclick(view: View) {
        val yScroll = view.y
        scrollView1.run { scrollTo(0, yScroll.toInt()) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_ktv, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_definiton) {
            menu_onclick(textView1)
            Toast.makeText(applicationContext, "Its toast!", Toast.LENGTH_SHORT).show()
        }

        if (id == R.id.action_graph_ktv) {
            menu_onclick(imageView21)
        }

        if (id == R.id.action_rule_of_ktv) {
            menu_onclick(textView5)
        }

        if (id == R.id.action_example_of_trade) {
            menu_onclick(graphics)
        }

        return super.onOptionsItemSelected(item)
    }

}
