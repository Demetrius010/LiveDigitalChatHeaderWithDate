package space.livedigital.chat_sdk.example

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import space.livedigital.chat_sdk.ui.ChatInitializer

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        initChat()
    }

    private fun initChat(){
        ChatInitializer.initialize(context)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}