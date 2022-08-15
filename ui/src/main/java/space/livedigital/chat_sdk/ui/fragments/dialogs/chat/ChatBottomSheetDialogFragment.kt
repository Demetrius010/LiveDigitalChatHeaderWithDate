package space.livedigital.chat_sdk.ui.fragments.dialogs.chat

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.databinding.ChatFragmentBinding
import space.livedigital.chat_sdk.ui.extensions.ListExtension
import space.livedigital.chat_sdk.ui.fragments.dialogs.base.BaseRoundedBottomSheetDialogFragment
import space.livedigital.chat_sdk.ui.list.adapters.chat.ChatAdapter
import space.livedigital.chat_sdk.ui.list.decorations.ChatItemDecoration
import space.livedigital.chat_sdk.ui.list.generators.ChatGenerator

/**
 * [BottomSheetDialogFragment] для экрана чата
 */
class ChatBottomSheetDialogFragment : BaseRoundedBottomSheetDialogFragment() {

    private var binding: ChatFragmentBinding? = null
    private lateinit var adapter: ChatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChatFragmentBinding.bind(view)

        setupList()
        showMessages()
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

    private fun showMessages() {
        val messages = ChatGenerator().generateMessagesListItems()
        adapter.updateItems(messages)
    }
}