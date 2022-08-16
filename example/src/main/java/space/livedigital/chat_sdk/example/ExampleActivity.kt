package space.livedigital.chat_sdk.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.livedigital.chat_sdk.example.databinding.ExampleActivityBinding
import space.livedigital.chat_sdk.ui.ui.fragments.dialogs.chat.ChatBottomSheetDialogFragment

class ExampleActivity : AppCompatActivity() {

    private lateinit var binding: ExampleActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExampleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupListeners()
    }

    private fun setupListeners() {
        binding.chatButton.setOnClickListener { showChat() }
    }

    private fun showChat() {
        val chatBottomSheetDialog = ChatBottomSheetDialogFragment()
        chatBottomSheetDialog.show(supportFragmentManager, TAG)
    }

    private companion object {
        const val TAG = "ChatBottomSheet"
    }
}