package space.livedigital.chat_sdk.ui.data.entities

/**
 * Entity сообщения
 */
data class Message(
    val id: String?,
    val text: String?,
    val created: String?,
    val author: String?,
    val type: String?,
    val sendStatus: String?,
    val pinned: Boolean,
)