package space.livedigital.chat_sdk.ui.data.repositories.chat

import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.domain.results.ExecutionResult

/**
 *  Репозиторий для предоставления данных чату
 */
class ChatRepository {

    suspend fun getMessages(): ExecutionResult<List<Message>> {
        return ExecutionResult.Success(generateMessages())
    }

    private fun generateMessages(): List<Message> {
        return listOf()
    }
}