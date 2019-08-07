package com.znuri.submission1.ui.Home

import com.znuri.submission1.services.Api.ApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter : HomeView.Presenter<HomeView.View> {
    lateinit var view:HomeView.View
    val compositeDisposable = CompositeDisposable()
    fun init(view:HomeView.View){
        this.view = view
    }
    val api by lazy {
        ApiServices.create()
    }
    override fun getTeams() {
        val subs = api.getAllTeams()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                view.onLoading()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.onSuccess(it)
            },{
                it.message?.let { it -> view.onError(it) }
            })
        compositeDisposable.add(subs)
    }
}