package space.livedigital.chat_sdk.ui

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import space.livedigital.chat_sdk.ui.di.modelsModule
import space.livedigital.chat_sdk.ui.di.repositoriesModule
import space.livedigital.chat_sdk.ui.di.viewModelsModule

/**
 * Инициализатор ui модуля chat_sdk
 */
object ChatInitializer {

    lateinit var context: Context
    lateinit var koinApplication: KoinApplication

    fun initialize(context: Context) {
        ChatInitializer.context = context

        initKoin()
    }

    private fun initKoin() {
        koinApplication = koinApplication {
            androidContext(context.applicationContext)
            modules(
                modelsModule,
                repositoriesModule,
                viewModelsModule
            )
        }
    }
}