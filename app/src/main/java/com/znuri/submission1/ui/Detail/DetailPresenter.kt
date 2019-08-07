package com.znuri.submission1.ui.Detail

import com.znuri.submission1.services.Api.ApiServices
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter : DetailView.Presenter<DetailView.View> {
    lateinit var view:DetailView.View
    val compositeDisposable = CompositeDisposable()
    fun init(view:DetailView.View){
        this.view = view
    }

    val api by lazy {
        ApiServices.create()
    }

    override fun getDetail(id:String?) {
        var dispose = api.getDetail(id)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                view.onLoading()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onSuccess(it.teams?.get(0))
            },{
                view.onFailed()
            })
        compositeDisposable.add(dispose)
    }
}