package com.vikrant.eventshowcase

import android.app.Application
import com.vikrant.core.database.database_module
import com.vikrant.core.retrofitclient.module.retrofit_module
import com.vikrant.event.eventList.module.event_module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EventListApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@EventListApp)
            modules(
                retrofit_module + event_module + database_module
            )
        }
    }
}