package space.livedigital.chat_sdk.ui.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Класс-Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}