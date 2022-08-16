package space.livedigital.chat_sdk.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import space.livedigital.chat_sdk.ui.ui.view_models.chat.ChatViewModel

/**
 * Koin module для экземпляров ViewModel
 */
val viewModelsModule = module {

    viewModel {
        ChatViewModel(chatModel = get())
    }
}