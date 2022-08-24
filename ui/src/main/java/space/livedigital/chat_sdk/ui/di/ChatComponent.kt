package space.livedigital.chat_sdk.ui.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import space.livedigital.chat_sdk.ui.ChatInitializer

/**
 * Расширение интерфейса [KoinComponent] для получения экземпляров непосредственно из контейнера Koin.
 * Реализуя данный интерфес, класс получает доступ к Inject методам Koin: by viewModel(), by inject()...
 */
interface ChatComponent : KoinComponent {

    override fun getKoin(): Koin {
        return ChatInitializer.koinApplication.koin
    }
}