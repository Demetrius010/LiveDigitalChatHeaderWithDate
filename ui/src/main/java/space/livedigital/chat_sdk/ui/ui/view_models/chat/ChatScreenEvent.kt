package space.livedigital.chat_sdk.ui.ui.view_models.chat

import space.livedigital.chat_sdk.ui.data.entities.Message

/**
 * События экрана чата
 */
sealed class ChatScreenEvent {
    data class ShowPinnedMessages(val pinnedMessages: List<Message>) : ChatScreenEvent()
}