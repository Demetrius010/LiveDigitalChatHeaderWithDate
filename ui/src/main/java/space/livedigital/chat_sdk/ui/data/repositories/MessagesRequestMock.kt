package space.livedigital.chat_sdk.ui.data.repositories

import space.livedigital.chat_sdk.ui.data.entities.Message
import java.util.*

/**
 *  Мок запроса на получение сообщений
 */
object MessagesRequestMock {

    private val dates = listOf(
        "2022-08-19T02:21:21.178Z", "2022-08-20T03:35:21.178Z", "2022-08-21T04:39:21.178Z",
        "2022-08-22T05:48:21.178Z", "2022-08-23T05:48:21.178Z"
    )
    private val users = listOf(
        "Михаил Кузин", "Анна Нестерова", "Василиса Данилова", "Ибрагим Чернов", "Георгий Титов",
        "Анна Андреева", "Андрей Орлов", "Екатерина Зайцева", "Владислав Савин", "Арсений Павлов"
    )

    fun generateMessages(): List<Message> {
        val listItems = mutableListOf<Message>()
        (0..12).forEach {
            val UTCDateString = dates[it / 3] // через каждые 3 сообщения меняем дату
            listItems.add(generateMessage(UTCDateString))
        }
        return listItems
    }

    private fun generateMessage(UTCDate: String): Message {
        return Message(
            id = UUID.randomUUID().toString().substring(0, 4),
            text = getRandomMessageText(),
            created = UTCDate,
            author = users.shuffled().first(),
            type = MessageType.user.name,
            sendStatus = MessageSendStatus.sent.name,
            pinned = false
        )
    }

    private fun getRandomMessageText(): String {
        val loremIpsum =
            "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. " +
                    "Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала " +
                    "XVI века. В то время безымянный печатник создал большую коллекцию размеров" +
                    " и форм шрифтов, используя Lorem Ipsum для образцов."
        return loremIpsum.substring(
            (0..loremIpsum.length / 2).random(),
            (loremIpsum.length / 2..(loremIpsum.length - 1)).random()
        )
    }
}

enum class MessageType {
    user,
    system
}

enum class MessageSendStatus {
    sending,
    sent,
    failed
}