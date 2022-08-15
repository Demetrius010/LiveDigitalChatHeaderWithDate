package space.livedigital.chat_sdk.ui.list.view_holders.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.data.entities.list_item.date.DateItemData
import space.livedigital.chat_sdk.ui.databinding.DateItemBinding
import space.livedigital.chat_sdk.ui.list.view_holders.base.BaseViewHolder
import java.text.SimpleDateFormat

class DateViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(inflater, parent, R.layout.date_item) {

    private val binding = DateItemBinding.bind(itemView)

    fun bind(listItem: ListItem) {
        val itemDate = listItem.data as DateItemData
        showMessage(itemDate)
    }

    private fun showMessage(data: DateItemData) {
        val month_date = SimpleDateFormat("MMMM dd")
        val month_name: String = month_date.format(data.date.time)
        binding.timeText.text = month_name.replaceFirstChar { it.uppercase() }
    }
}