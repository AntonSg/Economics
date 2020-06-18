package com.example.economics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_kpv.*
import kotlinx.android.synthetic.main.activity_kpv.scrollView1
import kotlinx.android.synthetic.main.activity_kpv.textView5
import kotlinx.android.synthetic.main.activity_ktv.*

class activity_elasticity : AppCompatActivity() {

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
        setContentView(R.layout.activity_elasticity)
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


}
