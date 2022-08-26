package space.livedigital.chat_sdk.ui.ui.fragments.dialogs.chat

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.databinding.ChatFragmentBinding
import space.livedigital.chat_sdk.ui.di.ChatComponent
import space.livedigital.chat_sdk.ui.ui.extensions.ListExtension
import space.livedigital.chat_sdk.ui.ui.extensions.repeatOnViewLifecycleStart
import space.livedigital.chat_sdk.ui.ui.fragments.dialogs.base.BaseRoundedBottomSheetDialogFragment
import space.livedigital.chat_sdk.ui.ui.list.adapters.chat.ChatAdapter
import space.livedigital.chat_sdk.ui.ui.list.decorations.ChatItemDecoration
import space.livedigital.chat_sdk.ui.ui.list.generators.ChatGenerator
import space.livedigital.chat_sdk.ui.ui.view_models.chat.ChatScreenEvent
import space.livedigital.chat_sdk.ui.ui.view_models.chat.ChatScreenState
import space.livedigital.chat_sdk.ui.ui.view_models.chat.ChatViewModel
import space.livedigital.chat_sdk.utils.ResourcesUtils

/**
 * [BottomSheetDialogFragment] для экрана чата
 */
class ChatBottomSheetDialogFragment : BaseRoundedBottomSheetDialogFragment(), ChatComponent {

    private val chatViewModel by viewModel<ChatViewModel>()

    private var binding: ChatFragmentBinding? = null
    private lateinit var adapter: ChatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChatFragmentBinding.bind(view)

        setupDialogHeight()
        setupList()
        setupListeners()
        observeState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun getLayoutResource() = R.layout.chat_fragment

    private fun setupDialogHeight() {
        dialog?.setOnShowListener { dialogInterface ->
            dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                ?.let { bottomSheet ->
                    val behavior = BottomSheetBehavior.from(bottomSheet)
                    val maxHeight = ResourcesUtils.getScreenHeightInPixels()

                    bottomSheet.layoutParams.height = maxHeight

                    behavior.peekHeight = maxHeight
                    behavior.maxHeight = maxHeight
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
        }
    }

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
                is ChatScreenState.Success -> showChatSuccessState(state)
                is ChatScreenState.Error -> showChatErrorState(state)
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
        binding?.list?.isVisible = false
    }

    private fun showChatSuccessState(state: ChatScreenState.Success) {
        binding?.list?.isVisible = true
        showMessages(state.messages)
    }

    private fun showChatErrorState(state: ChatScreenState.Error) {
        // TODO: Показываем экран/сообщение об ошибке
    }

    private fun setupListeners() {
        binding?.closeButton?.setOnClickListener {
            dismiss()
        }
    }

    private fun showMessages(messages: List<Message>) {
        val listItems = ChatGenerator().generateChatListItems(messages)
        adapter.updateItems(listItems)
    }

    private fun showPinnedMessages(messages: List<Message>) {
        // TODO: Показываем экран с закрепленными сообщениями
    }
}