package com.znuri.submission1.services.Utils

import android.content.Context
import android.widget.Toast
import android.view.*
class Util {

}

fun message(context: Context?,message:String?){
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}

fun View.toGone(){
    this.visibility = View.GONE
}
fun View.visible(){
    this.visibility = View.VISIBLE
}