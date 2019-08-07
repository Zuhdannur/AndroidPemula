package com.znuri.submission1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.znuri.submission1.ui.Home.HomeActivity

class MainActivity : AppCompatActivity() {

    val TIMEOUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            finish()
        },TIMEOUT)
    }
}
