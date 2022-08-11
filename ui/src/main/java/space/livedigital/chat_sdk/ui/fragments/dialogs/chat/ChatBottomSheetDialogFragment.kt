package space.livedigital.chat_sdk.ui.fragments.dialogs.chat

import android.os.Bundle
import android.view.View
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.databinding.ChatFragmentBinding
import space.livedigital.chat_sdk.ui.fragments.dialogs.base.BaseRoundedBottomSheetDialogFragment

class ChatBottomSheetDialogFragment : BaseRoundedBottomSheetDialogFragment() {

    private var binding: ChatFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChatFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun getLayoutResource() = R.layout.chat_fragment
}