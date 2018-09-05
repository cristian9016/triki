package com.example.personal.triki

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var movements = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111")
        adView.loadAd(AdRequest.Builder().build())

        tv1.setOnClickListener(this)
        tv2.setOnClickListener(this)
        tv3.setOnClickListener(this)
        tv9.setOnClickListener(this)
        tv7.setOnClickListener(this)
        tv8.setOnClickListener(this)
        tv5.setOnClickListener(this)
        tv6.setOnClickListener(this)
        tv4.setOnClickListener(this)
        restart.setOnClickListener {
            restart()
        }

    }

    override fun onClick(view: View?) {
        val tv = view as TextView
        if (movements != 8) restart.visibility = View.INVISIBLE
        else restart.visibility = View.VISIBLE
        if (tv.text.isNullOrEmpty()) {
            if (movements % 2 == 0) { tv.text = "X" }
            else { tv.text = "O" }
            checkWinner()
            movements += 1
        } else Toast.makeText(this, "No permitido", Toast.LENGTH_SHORT).show()
    }

    fun restart() {
        movements = 0
        restart.visibility = View.INVISIBLE
        tv1.text = ""
        tv2.text = ""
        tv3.text = ""
        tv4.text = ""
        tv5.text = ""
        tv6.text = ""
        tv7.text = ""
        tv8.text = ""
        tv9.text = ""
    }

    private fun checkWinner() {
        val value = if (movements % 2 == 0) "X"
        else "O"
        if(tv1.text == value && tv2.text == value && tv3.text == value
        || tv4.text == value && tv5.text == value && tv6.text == value
        || tv7.text == value && tv8.text == value && tv9.text == value
        || tv1.text == value && tv4.text == value && tv7.text == value
        || tv2.text == value && tv5.text == value && tv8.text == value
        || tv3.text == value && tv6.text == value && tv9.text == value
        || tv1.text == value && tv5.text == value && tv9.text == value
        || tv3.text == value && tv5.text == value && tv7.text == value){
            if(value == "X") showMessage(1)
            else showMessage(2)
        }
    }

    private fun showMessage(player:Int){
        alert {
            title = "Felicitaciones"
            message = "El Jugador $player Ha Ganado"
            positiveButton("OK") {
                restart()
            }
        }.show()
    }
}
