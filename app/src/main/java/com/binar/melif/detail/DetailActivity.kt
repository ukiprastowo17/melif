package com.binar.melif.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binar.melif.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
    }
}