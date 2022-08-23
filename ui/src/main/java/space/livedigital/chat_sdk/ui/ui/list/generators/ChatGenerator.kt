package space.livedigital.chat_sdk.ui.ui.list.generators

import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.ui.data.constants.list.ListItemIds
import space.livedigital.chat_sdk.ui.ui.data.constants.list.ListItemTypes
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DateItemData
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayTypeRelativeToToday
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayTypeRelativeToToday.*
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.message.MessageItemData
import space.livedigital.chat_sdk.utils.DateUtils
import space.livedigital.chat_sdk.utils.DateUtils.isDayBeforeYesterdayInUTC
import space.livedigital.chat_sdk.utils.DateUtils.isTodayInUTC
import space.livedigital.chat_sdk.utils.DateUtils.isYesterdayInUTC
import java.util.*

/**
 * Генератор элементов чата
 */
class ChatGenerator {

    fun generateChatListItems(messages: List<Message>): List<ListItem> {

        val messagesByDate = groupMessagesByDate(messages)

        return generateChatListItems(messagesByDate)
    }

    private fun groupMessagesByDate(messages: List<Message>): Map<Date, List<Message>> {
        val messagesByDate = LinkedHashMap<Date, MutableList<Message>>()
        messages.forEach { message ->
            val date = getDateWithoutTime(message.created!!)
            messagesByDate.getOrPut(date) { mutableListOf() }.add(message)
        }
        return messagesByDate
    }

    private fun generateChatListItems(messagesByDate: Map<Date, List<Message>>): List<ListItem> {
        val listItems = mutableListOf<ListItem>()
        messagesByDate.forEach { date, messages ->
            listItems.add(generateDateListItem(date))
            messages.forEach { message ->
                listItems.add(generateMessageListItem(message))
            }
        }
        return listItems
    }

    private fun generateMessageListItem(message: Message): ListItem {
        return ListItem(
            id = ListItemIds.MESSAGE.name,
            type = ListItemTypes.SELECTION,
            data = MessageItemData(message)
        )
    }

    private fun generateDateListItem(date: Date): ListItem {
        return ListItem(
            id = ListItemIds.DATE.name,
            type = ListItemTypes.TEXT,
            data = DateItemData(date, getDayType(date))
        )
    }

    private fun getDayType(date: Date): DayTypeRelativeToToday {
        return when {
            isTodayInUTC(date) -> TODAY
            isYesterdayInUTC(date) -> YESTERDAY
            isDayBeforeYesterdayInUTC(date) -> DAY_BEFORE_YESTERDAY
            else -> OTHER_DAYS
        }
    }

    private fun getDateWithoutTime(UTCDateString: String) =
        DateUtils.getDateWithoutTime(getUTCDate(UTCDateString)!!)

    private fun getUTCDate(UTCDateString: String) = DateUtils.getUTCDateFromFormattedString(
        UTCDateString,
        DateUtils.DateFormat.ISO_8601_WITH_TIME_ZONE
    )
}