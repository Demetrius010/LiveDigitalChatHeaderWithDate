package space.livedigital.chat_sdk.ui.di

import org.koin.dsl.module
import space.livedigital.chat_sdk.ui.domain.models.chat.ChatModel
import space.livedigital.chat_sdk.ui.domain.models.chat.ChatModelProd

/**
 * Koin module для экземпляров моделей
 */
val modelsModule = module {

    factory<ChatModel> {
        ChatModelProd(chatRepository = get())
    }
}