package com.example.td1.presentation.list

import android.app.Application
import android.content.Context

class GhibliApplication : Application(){

    companion object {
        var context: Context? = null

    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}
