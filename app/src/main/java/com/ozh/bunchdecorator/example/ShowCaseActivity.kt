package com.ozh.bunchdecorator.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_case.*

class ShowCaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_case)
        initViews()
    }

    private fun initViews() {
        linear_decor_btn.setOnClickListener {
            startActivity(Intent(this, LinearDecoratorActivity::class.java))
        }

        round_decor_btn.setOnClickListener {
            startActivity(Intent(this, RoundedDecoratorActivity::class.java))
        }
    }
}
