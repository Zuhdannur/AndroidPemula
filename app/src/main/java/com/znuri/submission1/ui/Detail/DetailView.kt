package com.znuri.submission1.ui.Detail

import com.znuri.submission1.services.model.ItemTeams

interface DetailView {
    interface View{
        fun onSuccess(itemTeams: ItemTeams?)
        fun onFailed()

        fun onLoading()

    }

    interface Presenter<in T>{
        fun getDetail(id:String?)
    }
}