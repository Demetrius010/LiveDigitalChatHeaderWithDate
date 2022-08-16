package space.livedigital.chat_sdk.ui.domain.models.chat

import space.livedigital.chat_sdk.ui.data.repositories.chat.ChatRepository

/**
 * Реализация [ChatModel]
 */
class ChatModelProd(private val chatRepository: ChatRepository) : ChatModel {

    override suspend fun getMessages() = chatRepository.getMessages()
}