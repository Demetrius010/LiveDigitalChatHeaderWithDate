package space.livedigital.chat_sdk.ui.data.entities

/**
 * Entity сообщения
 */
data class Message(
    val id: MessageId?,
    val text: String?,
    val created: String?,
    val author: UserId?,
    val type: MessageType?,
    val sendStatus: MessageSendStatus?,
    val pinned: Boolean,
)