package com.example.economics

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_kpv.*
import java.util.*


class activity_kpv : AppCompatActivity() {


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
        setContentView(R.layout.activity_kpv)
        setSupportActionBar(findViewById(R.id.toolbar1))

        val linechart1 = findViewById(R.id.line_chart1) as LineChart

        val y_kpv_1 = 100
        val x_kpv_1 = 90
        val y_kpv_2 = 200
        val x_kpv_2 = 100

        val kpv_1 = ArrayList<Entry>()
        kpv_1.add(Entry(0f, y_kpv_1.toInt() + 0f))
        kpv_1.add(Entry(x_kpv_1.toInt() + 0f, 0f))

        val kpv_1_gr = LineDataSet(kpv_1, "КПВ_1")
        kpv_1_gr.setDrawValues(true)
        kpv_1_gr.lineWidth = 2f
        kpv_1_gr.setColor(Color.rgb(133, 3, 3))
        kpv_1_gr.setCircleColor(Color.rgb(133, 3, 3))

        val kpv_2 = ArrayList<Entry>()
        kpv_2.add(Entry(0f, y_kpv_2.toInt() + 0f))
        kpv_2.add(Entry(x_kpv_2.toInt() + 0f, 0f))

        val kpv_2_gr = LineDataSet(kpv_2, "КПВ_2")
        kpv_2_gr.setDrawValues(true)
        kpv_2_gr.lineWidth = 2f
        kpv_2_gr.setColor(Color.rgb(11, 116, 0))
        kpv_2_gr.setCircleColor(Color.rgb(11, 116, 0))

        val kpv_1_2 = ArrayList<Entry>()
        val k1 = x_kpv_1.toFloat()/y_kpv_1.toFloat()
        val k2 = x_kpv_2.toFloat()/y_kpv_2.toFloat()

        if (k1 > k2) {
            kpv_1_2.add(Entry(0f, y_kpv_1.toInt() + y_kpv_2.toInt() + 0f))
            kpv_1_2.add(Entry(x_kpv_1.toInt() + 0f, y_kpv_2.toInt() + 0f))
            kpv_1_2.add(Entry(x_kpv_1.toInt() + x_kpv_2.toInt() + 0f, 0f))
        } else {
            kpv_1_2.add(Entry(0f, y_kpv_1.toInt() + y_kpv_2.toInt() + 0f))
            kpv_1_2.add(Entry(x_kpv_2.toInt() + 0f, y_kpv_1.toInt() + 0f))
            kpv_1_2.add(Entry(x_kpv_1.toInt() + x_kpv_2.toInt() + 0f, 0f))
        }

        val kpv_1_2_gr = LineDataSet(kpv_1_2, "Суммарное КПВ 1 и 2")
        kpv_1_2_gr.setDrawValues(true)
        kpv_1_2_gr.lineWidth = 2f
        kpv_1_2_gr.setColor(Color.rgb(0, 0, 0))
        kpv_1_2_gr.setCircleColor(Color.rgb(0, 0, 0))

        linechart1.data = LineData(kpv_1_gr, kpv_2_gr, kpv_1_2_gr)


        val seek1 = findViewById<SeekBar>(R.id.seekBar1)
        seek1?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek1: SeekBar,
                                           progress: Int, fromUser: Boolean) {

                val x_kpv_1 = seek1.progress
                kpv_1.clear()
                kpv_1_2.clear()
                kpv_1.add(Entry(0f,y_kpv_1.toInt()+0f))
                kpv_1.add(Entry(x_kpv_1.toInt()+0f, 0f))
                val k1 = x_kpv_1.toFloat()/y_kpv_1.toFloat()
                val k2 = x_kpv_2.toFloat()/y_kpv_2.toFloat()

                if(k1 > k2){
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+0f,y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }else{
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_2.toInt()+0f,y_kpv_1.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }

                linechart1.data = LineData(kpv_1_gr,kpv_2_gr, kpv_1_2_gr)
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

        val seek2 = findViewById<SeekBar>(R.id.seekBar2)
        seek2?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek2: SeekBar,
                                           progress: Int, fromUser: Boolean) {

                val y_kpv_1 = seek2.progress
                kpv_1.clear()
                kpv_1_2.clear()
                kpv_1.add(Entry(0f,y_kpv_1.toInt()+0f))
                kpv_1.add(Entry(x_kpv_1.toInt()+0f, 0f))
                val k1 = x_kpv_1.toFloat()/y_kpv_1.toFloat()
                val k2 = x_kpv_2.toFloat()/y_kpv_2.toFloat()

                if(k1 > k2){
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+0f,y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }else{
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_2.toInt()+0f,y_kpv_1.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }

                linechart1.data = LineData(kpv_1_gr,kpv_2_gr, kpv_1_2_gr)
            }



            override fun onStartTrackingTouch(seek2: SeekBar) {

            }

            override fun onStopTrackingTouch(seek2: SeekBar) {
                // write custom code for progress is stopped
                Toast.makeText(applicationContext,
                    "Progress is: " + seek2.progress + "%",
                    Toast.LENGTH_SHORT).show()

            }

        })

        val seek3 = findViewById<SeekBar>(R.id.seekBar3)
        seek3?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek3: SeekBar,
                                           progress: Int, fromUser: Boolean) {

                val x_kpv_2 = seek3.progress
                kpv_2.clear()
                kpv_1_2.clear()
                kpv_2.add(Entry(0f,y_kpv_2.toInt()+0f))
                kpv_2.add(Entry(x_kpv_2.toInt()+0f, 0f))
                val k1 = x_kpv_1.toFloat()/y_kpv_1.toFloat()
                val k2 = x_kpv_2.toFloat()/y_kpv_2.toFloat()

                if(k1 > k2){
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+0f,y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }else{
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_2.toInt()+0f,y_kpv_1.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }

                linechart1.data = LineData(kpv_1_gr,kpv_2_gr, kpv_1_2_gr)
            }

            override fun onStartTrackingTouch(seek3: SeekBar) {

            }

            override fun onStopTrackingTouch(seek3: SeekBar) {
                // write custom code for progress is stopped
                Toast.makeText(applicationContext,
                    "Progress is: " + seek3.progress + "%",
                    Toast.LENGTH_SHORT).show()

            }

        })

        val seek4 = findViewById<SeekBar>(R.id.seekBar4)
        seek4?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek4: SeekBar,
                                           progress: Int, fromUser: Boolean) {

                val y_kpv_2 = seek4.progress
                kpv_2.clear()
                kpv_1_2.clear()
                kpv_2.add(Entry(0f,y_kpv_2.toInt()+0f))
                kpv_2.add(Entry(x_kpv_2.toInt()+0f, 0f))
                val k1 = x_kpv_1.toFloat()/y_kpv_1.toFloat()
                val k2 = x_kpv_2.toFloat()/y_kpv_2.toFloat()

                if(k1 > k2){
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+0f,y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }else{
                    kpv_1_2.add(Entry(0f, y_kpv_1.toInt()+y_kpv_2.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_2.toInt()+0f,y_kpv_1.toInt()+0f))
                    kpv_1_2.add(Entry(x_kpv_1.toInt()+x_kpv_2.toInt()+0f,0f))
                }

                linechart1.data = LineData(kpv_1_gr,kpv_2_gr, kpv_1_2_gr)
            }

            override fun onStartTrackingTouch(seek4: SeekBar) {

            }

            override fun onStopTrackingTouch(seek4: SeekBar) {
                // write custom code for progress is stopped
                Toast.makeText(applicationContext,
                    "Progress is: " + seek4.progress + "%",
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
            inflater.inflate(R.menu.menu_kpv, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            val id = item.itemId

            if (id == R.id.action_definiton) {
                menu_onclick(textView2)
                Toast.makeText(applicationContext, "Its toast!", Toast.LENGTH_SHORT).show()
            }

            if (id == R.id.action_graph_kpv) {
                menu_onclick(textView3)
            }

            if (id == R.id.action_alternativ_cost) {
                menu_onclick(imageView3)
            }

            if (id == R.id.action_summing_kpv) {
                menu_onclick(textView7)
            }

            return super.onOptionsItemSelected(item)
        }


}


