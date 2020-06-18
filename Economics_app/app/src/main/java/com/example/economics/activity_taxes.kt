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
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_kpv.*
import kotlinx.android.synthetic.main.activity_kpv.scrollView1
import kotlinx.android.synthetic.main.activity_kpv.textView5
import kotlinx.android.synthetic.main.activity_ktv.*
import kotlinx.android.synthetic.main.activity_ktv.textView1
import kotlinx.android.synthetic.main.activity_taxes.*


class activity_taxes : AppCompatActivity() {

    public val seek_value_public = 50

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
        setContentView(R.layout.activity_taxes)
        setSupportActionBar(findViewById(R.id.toolbar3))

        val linechart1 = findViewById(R.id.line_chart1) as LineChart

        val demand = ArrayList<Entry>()
        demand.add(Entry(0f,100f))
        demand.add(Entry(100f, 0f))

        val demand_gr = LineDataSet(demand,"Предложение")
        demand_gr.setDrawValues(false)
        demand_gr.lineWidth = 3f
        demand_gr.setColor(Color.rgb(133, 3, 3))
        demand_gr.setCircleColor(Color.rgb(133, 3, 3))


        val supply = ArrayList<Entry>()
        supply.add(Entry(0f,0f))
        supply.add(Entry(100f,100f))

        val supply_gr = LineDataSet(supply,"Спрос")
        demand_gr.setDrawValues(false)
        demand_gr.lineWidth = 3f
        demand_gr.setColor(Color.rgb(11, 116, 0))
        demand_gr.setCircleColor(Color.rgb(11, 116, 0))

        val eq_dot = ArrayList<Entry>()
        eq_dot.add(Entry(seek_value_public.toInt()+0f,seek_value_public.toInt()+0f))

        val eq = LineDataSet(eq_dot, "Точка равновесия")
        eq.lineWidth = 3f
        eq.setCircleColor(Color.rgb(124, 10, 199))
        eq.setColor(Color.rgb(124, 10, 199))

        val linechik = ArrayList<Entry>()
        linechik.add(Entry(0f,seek_value_public.toInt()+0f))
        linechik.add(Entry(seek_value_public.toInt()+0f,seek_value_public.toInt()+0f))

        val linechik_gr = LineDataSet(linechik,"Потолок цены")
        linechik_gr.setDrawValues(false)
        linechik_gr.lineWidth = 1f
        linechik_gr.setCircleColor(Color.rgb(0,0,1))
        linechik_gr.setColor(Color.rgb(0,0,1))

        linechart1.data = LineData(eq,demand_gr,supply_gr,linechik_gr)

        val seek1 = findViewById<SeekBar>(R.id.seekBar1)
        seek1?.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek1: SeekBar,
                                           progress: Int, fromUser: Boolean) {

                val seek_value_public = seek1.progress


                if(seek_value_public > 50 ){
                    eq_dot.clear()
                    eq_dot.add(Entry(50f,50f))
                    linechik.clear()
                    linechik.add(Entry(0f,seek_value_public.toInt()+0f))
                    linechik.add(Entry(seek_value_public.toInt()+0f,seek_value_public.toInt()+0f))
                }
                else{
                    eq_dot.clear()
                    eq_dot.add(Entry(seek_value_public.toFloat(),seek_value_public.toFloat()))
                    linechik.clear()
                    linechik.add(Entry(0f,seek_value_public.toInt()+0f))
                    linechik.add(Entry(seek_value_public.toInt()+0f,seek_value_public.toInt()+0f))
                }

                linechart1.data = LineData(demand_gr,supply_gr,linechik_gr,eq)
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
        inflater.inflate(R.menu.menu_taxes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_definiton) {
            menu_onclick(textView1)
            Toast.makeText(applicationContext, "Its toast!", Toast.LENGTH_SHORT).show()
        }

        if (id == R.id.action_image_of_graph) {
            menu_onclick(imageView1)
        }

        if (id == R.id.new_eq) {
            menu_onclick(button1)
        }

        return super.onOptionsItemSelected(item)
    }



}
