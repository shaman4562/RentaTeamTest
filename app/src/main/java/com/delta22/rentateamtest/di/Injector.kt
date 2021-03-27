package com.delta22.rentateamtest.di

import android.content.Context

object Injector {

    lateinit var appComponent: AppComponent

    fun init(context: Context) {
        appComponent = DaggerAppComponent.factory().create(context)
    }
}
