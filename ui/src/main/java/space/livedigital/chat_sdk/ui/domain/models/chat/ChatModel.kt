package space.livedigital.chat_sdk.ui.domain.models.chat

import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.domain.results.ExecutionResult

/**
 * Модель чата
 */
interface ChatModel {

    /**
     * Возвращает список сообщений
     */
    suspend fun getMessages(): ExecutionResult<List<Message>>
}