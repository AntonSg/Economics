package com.example.economics

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            val flag_r = 1
            setTheme(R.style.AppTheme2)
        }
        else {
            val flag_r = 0
            setTheme(R.style.AppTheme_main)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sw = findViewById(R.id.switch1) as Switch
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            sw.setChecked(true)
        }
        sw.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(applicationContext,
                    "Робит",
                    Toast.LENGTH_SHORT).show()
                restartApp()

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(applicationContext,
                    "НеРобит",
                    Toast.LENGTH_SHORT).show()
                restartApp()

            }
        }

    }

    fun restartApp() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


    fun kpv_onclick(view: View){
        val kpv2_intent = Intent(this, activity_kpv::class.java)
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            kpv2_intent.putExtra("flag", 1)
        }else{
            kpv2_intent.putExtra("flag", 0)
        }
        startActivity(kpv2_intent)

}

    fun ktv_onclick(view: View){
        val ktv2_intent = Intent(this, activity_ktv::class.java)
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            ktv2_intent.putExtra("flag", 1)
        }else{
            ktv2_intent.putExtra("flag", 0)
        }
        startActivity(ktv2_intent)
    }

    fun taxes_onclick(view: View){
        val taxes2_intent = Intent(this, activity_taxes::class.java)
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            taxes2_intent.putExtra("flag", 1)
        }else{
            taxes2_intent.putExtra("flag", 0)
        }
        startActivity(taxes2_intent)
    }

    fun elasticity_onclick(view: View){
        val elasticity_intent = Intent(this, activity_elasticity::class.java)
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            elasticity_intent.putExtra("flag", 1)
        }else{
            elasticity_intent.putExtra("flag", 0)
        }
        startActivity(elasticity_intent)
    }
}
