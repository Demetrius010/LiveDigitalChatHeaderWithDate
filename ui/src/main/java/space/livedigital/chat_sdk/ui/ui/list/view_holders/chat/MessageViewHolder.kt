package space.livedigital.chat_sdk.ui.ui.list.view_holders.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.databinding.MessageItemBinding
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.message.MessageItemData
import space.livedigital.chat_sdk.ui.ui.list.view_holders.base.BaseViewHolder
import space.livedigital.chat_sdk.utils.DateUtils

/**
 * ViewHolder сообщения
 */
class MessageViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(inflater, parent, R.layout.message_item) {

    private val binding = MessageItemBinding.bind(itemView)

    fun bind(listItem: ListItem) {
        val messageItemData = listItem.data as MessageItemData
        showMessage(messageItemData.message)
    }

    private fun showMessage(message: Message) {
        binding.messageText.text = message.text
        binding.nameText.text = message.author
        binding.initialsText.text = getUserInitials(message.author)
        binding.timeText.text = getUTCTime(message.created!!)
    }

    private fun getUserInitials(name: String?): String? {
        return name?.split(" ")?.map { it.first() }?.joinToString("")
    }

    private fun getUTCTime(UTCDateString: String): String {
        val UTCDate = DateUtils.getUTCDateFromFormattedString(
            UTCDateString,
            DateUtils.DateFormat.ISO_8601_WITH_TIME_ZONE
        )
        return DateUtils.getFormattedDateString(UTCDate, DateUtils.DateFormat.HH_MM)!!
    }
}