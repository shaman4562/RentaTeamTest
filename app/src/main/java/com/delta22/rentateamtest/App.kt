package com.delta22.rentateamtest

import android.app.Application
import com.delta22.rentateamtest.di.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}
