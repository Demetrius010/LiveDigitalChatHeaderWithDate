package space.livedigital.chat_sdk.ui.ui.view_models.chat

import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.domain.results.ExecutionError

/**
 * Состояния экрана чата
 */
sealed class ChatScreenState {
    object Initial : ChatScreenState()
    object Loading : ChatScreenState()
    class Error(val error: ExecutionError) : ChatScreenState()
    class Success(val messages: List<Message>) : ChatScreenState()
    object EmptyChat : ChatScreenState()
}