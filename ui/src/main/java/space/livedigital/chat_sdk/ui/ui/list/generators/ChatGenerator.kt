package space.livedigital.chat_sdk.ui.ui.list.generators

import space.livedigital.chat_sdk.ui.data.entities.Message
import space.livedigital.chat_sdk.ui.ui.data.constants.list.ListItemIds
import space.livedigital.chat_sdk.ui.ui.data.constants.list.ListItemTypes
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DateItemData
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayType
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayType.*
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.message.MessageItemData
import space.livedigital.chat_sdk.utils.DateUtils
import java.util.*

/**
 * Генератор элементов чата
 */
class ChatGenerator {

    fun generateChatListItems(messages: List<Message>): List<ListItem> {

        val messagesByDate = LinkedHashMap<Date, MutableList<Message>>()

        messages.forEach {
            val date = getDateWithoutTime(it.created!!)

            if (messagesByDate.containsKey(date)) {
                messagesByDate[date]?.add(it)
            } else {
                messagesByDate[date] = mutableListOf(it)
            }
        }

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

    private fun getDayType(date: Date): DayType {
        var dayType = OTHER_DAYS
        if (DateUtils.isTodayInUTC(date)) {
            dayType = TODAY
        } else if (DateUtils.isYesterdayInUTC(date)) {
            dayType = YESTERDAY
        } else if (DateUtils.isDayBeforeYesterdayInUTC(date)) {
            dayType = DAY_BEFORE_YESTERDAY
        }
        return dayType
    }

    private fun getDateWithoutTime(UTCDateString: String) =
        DateUtils.getDateWithoutTime(getUTCDate(UTCDateString)!!)

    private fun getUTCDate(UTCDateString: String) = DateUtils.getUTCDateFromFormattedString(
        UTCDateString,
        DateUtils.DateFormat.ISO_8601_WITH_TIME_ZONE
    )
}