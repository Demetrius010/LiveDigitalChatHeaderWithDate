package space.livedigital.chat_sdk.ui.ui.list.generators

import space.livedigital.chat_sdk.ui.data.entities.*
import space.livedigital.chat_sdk.ui.ui.data.constants.list.ListItemIds
import space.livedigital.chat_sdk.ui.ui.data.constants.list.ListItemTypes
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DateItemData
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayType
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayType.*
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.message.*
import space.livedigital.chat_sdk.utils.DateUtils
import java.util.*

/**
 * Генератор элементов чата
 */
class ChatGenerator {

    private val dates = listOf(
        "2022-08-12T04:39:21.178Z",
        "2022-08-13T02:21:21.178Z",
        "2022-08-14T03:35:21.178Z",
        "2022-08-15T04:39:21.178Z",
        "2022-08-16T05:48:21.178Z"
    )

    private var messageWasToday = false
    private var messageWasYesterday = false
    private var messageWasDayBeforeYesterday = false
    private var previousDate = Date(0)

    fun generateMessagesListItems(messages: List<Message>): List<ListItem> {
        val listItems = mutableListOf<ListItem>()

        (0..12).forEach {
            val UTCDateString = dates[it / 3]
            val UTCDate = getUTCDate(UTCDateString)!!
            if (!DateUtils.isTheSameDay(previousDate, UTCDate)) {
                previousDate = UTCDate
                listItems.add(generateDate(UTCDate))
            }

            listItems.add(generateMessage(UTCDate))
        }

        return listItems
    }

    private fun generateMessage(UTCDate: Date): ListItem {
        val messageItemData = MessageItemData(
            id = MessageId(UUID.randomUUID().toString().substring(0, 4)),
            text = getRandomMessage(),
            created = UTCDate,
            author = UserId(UUID.randomUUID().toString().substring(0, 5)),
            type = MessageType.USER,
            sendStatus = MessageSendStatus.SENT,
            pinned = false
        )
        return ListItem(
            id = ListItemIds.MESSAGE.name,
            type = ListItemTypes.SELECTION,
            data = messageItemData
        )
    }

    private fun getRandomMessage(): String {
        val loremIpsum =
            "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для образцов."
        return loremIpsum.substring(
            (0..loremIpsum.length / 2).random(),
            (loremIpsum.length / 2..(loremIpsum.length - 1)).random()
        )
    }

    private fun generateDate(date: Date): ListItem {
        return ListItem(
            id = ListItemIds.DATE.name,
            type = ListItemTypes.TEXT,
            data = DateItemData(date, getDayType(date))
        )
    }

    private fun getDayType(date: Date): DayType {
        var dayType = OTHER_DAYS
        if (!messageWasToday && DateUtils.isTodayInUTC(date)) {
            dayType = TODAY
            messageWasToday = true
        } else if (!messageWasYesterday && DateUtils.isYesterdayInUTC(date)) {
            dayType = YESTERDAY
            messageWasYesterday = true
        } else if (!messageWasDayBeforeYesterday && DateUtils.isDayBeforeYesterdayInUTC(date)) {
            dayType = DAY_BEFORE_YESTERDAY
            messageWasDayBeforeYesterday = true
        }
        return dayType
    }

    private fun getUTCDate(UTCDateString: String) = DateUtils.getUTCDateFromFormattedString(
        UTCDateString,
        DateUtils.DateFormat.ISO_8601_WITH_TIME_ZONE
    )
}