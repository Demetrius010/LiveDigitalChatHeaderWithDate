package space.livedigital.chat_sdk.ui.ui.list.adapters.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import space.livedigital.chat_sdk.ui.ui.data.constants.list.ListItemIds
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.list.adapters.base.BaseAdapter
import space.livedigital.chat_sdk.ui.ui.list.view_holders.base.BaseViewHolder
import space.livedigital.chat_sdk.ui.ui.list.view_holders.chat.DateViewHolder
import space.livedigital.chat_sdk.ui.ui.list.view_holders.chat.MessageViewHolder

/**
 * Adapter для ленты сообщений чата
 */
class ChatAdapter(layoutInflater: LayoutInflater) :
    BaseAdapter<ListItem, BaseViewHolder>(layoutInflater) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_MESSAGE -> MessageViewHolder(layoutInflater, parent)
            TYPE_DATE -> DateViewHolder(layoutInflater, parent)
            else -> throwUnknownViewHolderTypeException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ListItem) {
        when (holder.itemViewType) {
            TYPE_MESSAGE -> (holder as MessageViewHolder).bind(item)
            TYPE_DATE -> (holder as DateViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return when (item.id) {
            ListItemIds.MESSAGE.name -> TYPE_MESSAGE
            ListItemIds.DATE.name -> TYPE_DATE
            else -> NOT_FOUND
        }
    }

    private companion object {
        const val TYPE_MESSAGE = 1
        const val TYPE_DATE = 2
    }
}