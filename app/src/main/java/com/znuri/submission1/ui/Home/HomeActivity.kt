package com.znuri.submission1.ui.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.znuri.submission1.R
import com.znuri.submission1.services.Adapter.TeamAdapter
import com.znuri.submission1.services.Utils.message
import com.znuri.submission1.services.Utils.toGone
import com.znuri.submission1.services.Utils.visible
import com.znuri.submission1.services.model.ItemTeams
import com.znuri.submission1.services.model.TeamResponses
import com.znuri.submission1.ui.Detail.DetailActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(),HomeView.View ,TeamAdapter.onSelectedRecylerView{

    override fun onItemClick(itemTeams: ItemTeams?) {
        val bundle = Bundle()
        bundle.putString("id",itemTeams?.idTeam.toString())
        bundle.putString("title",itemTeams?.strTeam.toString())
        startActivity(Intent(this@HomeActivity,DetailActivity::class.java).putExtras(bundle))
    }

    lateinit var presenter:HomePresenter
    lateinit var adapter:TeamAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()

    }

    override fun onLoading() {
        loading.visible()
    }

    override fun onSuccess(data: TeamResponses?) {
        loading.toGone()
        rcData.visible()
        if(data?.teams !== null){

            rcData.apply {
                val adapts = TeamAdapter(applicationContext,data?.teams)
                adapts.init(this@HomeActivity)

                this.adapter = adapts
                this.layoutManager = LinearLayoutManager(applicationContext)
                this.setHasFixedSize(true)
                this.addItemDecoration(DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL))
            }
        }

    }

    override fun onError(message: String?) {
    }

    fun init(){
        presenter = HomePresenter()
        presenter.init(this)

        presenter.getTeams()

        adapter = TeamAdapter()

    }
}
