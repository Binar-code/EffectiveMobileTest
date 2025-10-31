package com.example.effectivemobiletest

import android.app.Application
import com.example.data.di.DatabaseModule
import com.example.data.di.RepositoryModule
import com.example.data.di.RetrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App(): Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                RetrofitModule,
                DatabaseModule,
                RepositoryModule
            )
        }
    }
}
