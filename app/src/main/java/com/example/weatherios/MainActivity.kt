package com.example.weatherios

import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.graphics.Typeface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherios.weather.BaseDrawer
import com.example.weatherios.weather.UiUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var mDrawerType = BaseDrawer.Type.UNKNOWN_D
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initOnClick()
        initWeather()
    }

    fun initOnClick() {
        btn_finish.setOnClickListener {
            finish()
        }
        btn_show.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            val strs = ArrayList<String>()
            for (t in BaseDrawer.Type.values()) {
                strs.add(t.toString())
            }
            var index = 0
            for (i in 0 until BaseDrawer.Type.values().size) {
                if (BaseDrawer.Type.values()[i] === mDrawerType) {
                    index = i
                    break
                }
            }
            builder.setSingleChoiceItems(
                strs.toTypedArray(), index
            ) { dialog, which ->
                mDrawerType = BaseDrawer.Type.values()[which]
                notifyActivityUpdate()
                dialog.dismiss()
                // Toast.makeText(getActivity(), "onClick->"
                // + which, Toast.LENGTH_SHORT).show();
            }
            builder.setNegativeButton(android.R.string.cancel, null)
            builder.create().show()
        }
    }

    private fun notifyActivityUpdate() {
        weatherView.setDrawerType(mDrawerType)
        weather.setDrawerType(mDrawerType)
    }

    fun initWeather() {
        weatherView.setDrawerType(BaseDrawer.Type.FOG_D)
        weather.setDrawerType(BaseDrawer.Type.FOG_D)
    }

    override fun onResume() {
        super.onResume()
        weatherView.onResume()
        weather.onResume()
    }

    override fun onPause() {
        super.onPause()
        weatherView.onPause()
        weather.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        weatherView.onDestroy()
        weather.onDestroy()
    }

}
