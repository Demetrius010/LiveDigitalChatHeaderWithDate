package space.livedigital.chat_sdk.ui.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import space.livedigital.chat_sdk.ui.di.modelsModule
import space.livedigital.chat_sdk.ui.di.repositoriesModule
import space.livedigital.chat_sdk.ui.di.viewModelsModule

/**
 * Класс - Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                modelsModule,
                repositoriesModule,
                viewModelsModule
            )
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}