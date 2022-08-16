package space.livedigital.chat_sdk.ui.ui.list.view_holders.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.data.entities.UserId
import space.livedigital.chat_sdk.ui.databinding.MessageItemBinding
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.message.MessageItemData
import space.livedigital.chat_sdk.ui.ui.list.view_holders.base.BaseViewHolder
import space.livedigital.chat_sdk.utils.DateUtils
import java.util.*

class MessageViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(inflater, parent, R.layout.message_item) {

    private val binding = MessageItemBinding.bind(itemView)

    fun bind(listItem: ListItem) {
        val messageItemData = listItem.data as MessageItemData
        showMessage(messageItemData)
    }

    private fun showMessage(data: MessageItemData) {
        binding.messageText.text = data.text

        val authorName = getAuthorName(data.author)
        binding.nameText.text = authorName
        binding.initialsText.text = authorName.split(" ").map { it.first() }.joinToString("")

        binding.timeText.text = getUTCTime(data.created)
    }

    private fun getUTCTime(UTCDate: Date) =
        DateUtils.getFormattedDateString(UTCDate, DateUtils.DateFormat.HH_MM)

    private fun getAuthorName(userId: UserId): String {
        val users = listOf(
            "Михаил Кузин",
            "Анна Нестерова",
            "Василиса Данилова",
            "Ибрагим Чернов",
            "Георгий Титов",
            "Анна Андреева",
            "Андрей Орлов",
            "Екатерина Зайцева",
            "Владислав Савин",
            "Арсений Павлов"
        )
        return users.shuffled().first()
    }
}