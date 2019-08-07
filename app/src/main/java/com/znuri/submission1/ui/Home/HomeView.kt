package com.znuri.submission1.ui.Home

import com.znuri.submission1.services.model.TeamResponses

interface HomeView {
    interface View {
        fun onLoading()
        fun onSuccess(data:TeamResponses?)
        fun onError(message:String?)
    }
    interface Presenter<in T>{
        fun getTeams()
    }
}