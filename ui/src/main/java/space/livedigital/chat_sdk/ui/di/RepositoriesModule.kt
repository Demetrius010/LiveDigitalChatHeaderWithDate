package space.livedigital.chat_sdk.ui.di

import org.koin.dsl.module
import space.livedigital.chat_sdk.ui.data.repositories.chat.ChatRepository

/**
 * Koin module для экземпляров репозиториев
 */
val repositoriesModule = module {

    factory {
        ChatRepository()
    }
}