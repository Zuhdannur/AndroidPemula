package com.znuri.submission1.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.znuri.submission1.R
import com.znuri.submission1.services.Utils.message
import com.znuri.submission1.services.Utils.toGone
import com.znuri.submission1.services.Utils.visible
import com.znuri.submission1.services.model.ItemTeams
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(),DetailView.View {
    lateinit var presenter:DetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        view()

        init()

    }

    fun init(){
        presenter = DetailPresenter()
        presenter.init(this)
        if(intent.extras !== null){
            val bundle  = intent.extras
            presenter.getDetail(bundle?.getString("id"))
        }

    }

    fun view(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(intent.extras !== null){
            val bundle  = intent.extras
            supportActionBar?.setDisplayShowTitleEnabled(true)
            supportActionBar?.title = bundle?.getString("title")
        }
    }

    override fun onLoading() {
        loading.visible()
    }

    override fun onSuccess(itemTeams: ItemTeams?) {
        loading.toGone()
    }

    override fun onFailed() {
        message(applicationContext,"Failed")
    }
}
