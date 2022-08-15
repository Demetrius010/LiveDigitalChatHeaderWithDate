package space.livedigital.chat_sdk.ui.list.generators

import space.livedigital.chat_sdk.ui.data.constants.list.ListItemIds
import space.livedigital.chat_sdk.ui.data.constants.list.ListItemTypes
import space.livedigital.chat_sdk.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.data.entities.list_item.date.DateItemData
import space.livedigital.chat_sdk.ui.data.entities.list_item.message.*
import java.util.*

/**
 * Генератор элементов чата
 */
class ChatGenerator {

    fun generateMessagesListItems(): List<ListItem> {
        val listItems = mutableListOf<ListItem>()

        (0..10).forEach {
            listItems.add(generateMessage())
        }

        listItems.add(3, generateDate())
        listItems.add(6, generateDate())

        return listItems
    }

    private fun generateMessage(): ListItem {
        val messageItemData = MessageItemData(
            id = MessageId(UUID.randomUUID().toString().substring(0, 4)),
            text = getRandomMessage(),
            created = "2022-08-15T04:39:21.178Z",
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

    fun getRandomMessage(): String {
        val loremIpsum =
            "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов."
        return loremIpsum.substring(
            (0..loremIpsum.length / 2).random(),
            (loremIpsum.length / 2..(loremIpsum.length - 1)).random()
        )
    }

    fun generateDate(): ListItem {
        return ListItem(
            id = ListItemIds.DATE.name,
            type = ListItemTypes.TEXT,
            data = DateItemData(Date())
        )
    }
}