package space.livedigital.chat_sdk.ui.ui.view_models.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.domain.models.chat.ChatModel
import space.livedigital.chat_sdk.ui.domain.results.ExecutionResult

/**
 * ViewModel чата
 */
class ChatViewModel(private val chatModel: ChatModel) : ViewModel() {

    private val screenEventChannel = Channel<ChatScreenEvent>(Channel.UNLIMITED)
    val screenEventFlow = screenEventChannel.receiveAsFlow()

    private val mutableScreenState = MutableStateFlow<ChatScreenState>(ChatScreenState.Initial)
    val screenStateFlow get() = mutableScreenState.asStateFlow()

    init {
        loadMessages()
    }

    fun onCloseButtonClicked() {

    }

    private fun loadMessages() {
        mutableScreenState.value = ChatScreenState.Loading
        viewModelScope.launch {
            val result = chatModel.getMessages()
            onLoadMessagesReceivedResult(result)
        }
    }

    private fun onLoadMessagesReceivedResult(result: ExecutionResult<List<Message>>) {
        when (result) {
            is ExecutionResult.Success -> {
                val messages = result.data
                mutableScreenState.value = if (messages.isEmpty()) {
                    ChatScreenState.EmptyChat
                } else {
                    ChatScreenState.Success(result.data)
                }

            }
            is ExecutionResult.Error ->
                mutableScreenState.value = ChatScreenState.Error(result.error)
        }
    }
}