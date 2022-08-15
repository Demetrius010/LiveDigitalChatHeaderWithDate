package space.livedigital.chat_sdk.ui.data.entities.list_item.message

import java.util.*

/**
 * Данные для [ListItem] сообщений чата
 */
data class MessageItemData(
    val id: MessageId,
    val text: String,
    val created: String,
    val author: UserId,
    val type: MessageType,
    val sendStatus: MessageSendStatus,
    val pinned: Boolean,
)