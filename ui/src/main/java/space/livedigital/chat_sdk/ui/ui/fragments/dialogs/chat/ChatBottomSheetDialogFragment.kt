package space.livedigital.chat_sdk.ui.ui.fragments.dialogs.chat

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.databinding.ChatFragmentBinding
import space.livedigital.chat_sdk.ui.ui.extensions.ListExtension
import space.livedigital.chat_sdk.ui.ui.extensions.repeatOnViewLifecycleStart
import space.livedigital.chat_sdk.ui.ui.fragments.dialogs.base.BaseRoundedBottomSheetDialogFragment
import space.livedigital.chat_sdk.ui.ui.list.adapters.chat.ChatAdapter
import space.livedigital.chat_sdk.ui.ui.list.decorations.ChatItemDecoration
import space.livedigital.chat_sdk.ui.ui.list.generators.ChatGenerator
import space.livedigital.chat_sdk.ui.ui.view_models.chat.ChatScreenEvent
import space.livedigital.chat_sdk.ui.ui.view_models.chat.ChatScreenState
import space.livedigital.chat_sdk.ui.ui.view_models.chat.ChatViewModel

/**
 * [BottomSheetDialogFragment] для экрана чата
 */
class ChatBottomSheetDialogFragment : BaseRoundedBottomSheetDialogFragment() {

    private val chatViewModel by viewModel<ChatViewModel>()

    private var binding: ChatFragmentBinding? = null
    private lateinit var adapter: ChatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChatFragmentBinding.bind(view)

        setupList()
        setupListeners()
        observeState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun getLayoutResource() = R.layout.chat_fragment

    private fun setupList() {
        adapter = ChatAdapter(layoutInflater)
        val listExtension = ListExtension(binding?.list)
        listExtension.setAdapter(adapter)
        listExtension.addItemDecoration(ChatItemDecoration())
    }


    private fun observeState() {
        repeatOnViewLifecycleStart {
            launch {
                observeScreenState()
            }

            launch {
                observeEvents()
            }
        }
    }

    private suspend fun observeScreenState() {
        chatViewModel.screenStateFlow.collect { state ->
            showInitialState()

            when (state) {
                is ChatScreenState.Initial -> {}
                is ChatScreenState.Loading -> showChatLoadingState()
                is ChatScreenState.Success -> showChatSussesState(state)
                is ChatScreenState.EmptyChat -> showEmptyChatState()
                is ChatScreenState.Error -> showErrorState(state)
            }
        }
    }

    private suspend fun observeEvents() {
        chatViewModel.screenEventFlow.collect { event ->
            when (event) {
                is ChatScreenEvent.ShowPinnedMessages -> {
                    showPinnedMessages(event.pinnedMessages)
                }
            }
        }
    }

    private fun showInitialState() {
//        binding?.headerView?.isVisible = false
//        binding?.list?.isVisible = false
//        binding?.closeButton?.isEnabled = true
//        binding?.inputView?.isVisible = false
//        binding?.loadingView?.isVisible = false
    }

    private fun showChatSussesState(state: ChatScreenState.Success) {
        //binding?.list?.isVisible = true
        showMessages(state.messages)
    }

    private fun showChatLoadingState() {
//        binding?.loadingView?.isVisible = true
    }

    private fun showErrorState(state: ChatScreenState.Error) {
//        binding?.errorStateView?.isVisible = true

//        val errorMessage = MessagesConverter().convert(error)
//        binding?.errorMessageText?.text = errorMessage
    }

    private fun showEmptyChatState() {
//        binding?.contentView?.isVisible = false
//        binding?.messagesNotFoundView?.isVisible = true
    }

    private fun setupListeners() {
        binding?.closeButton?.setOnClickListener {
            chatViewModel.onCloseButtonClicked()
        }
    }

    private fun showMessages(messages: List<Message>) {
        val listItems = ChatGenerator().generateMessagesListItems(messages)
        adapter.updateItems(listItems)
    }

    private fun showPinnedMessages(messages: List<Message>) {

    }
}