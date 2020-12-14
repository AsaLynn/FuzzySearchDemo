package com.zxn.fuzzysearchdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_enter.*

class EnterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)

        btn.setOnClickListener {
            MainActivity.jumpTo(this)
        }

        btn2.setOnClickListener {
            SearchActivity.jumpTo(this)
        }
    }
}