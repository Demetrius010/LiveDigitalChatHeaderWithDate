package space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date

import java.util.*

/**
 * Данные для [ListItem] заголовка с датой
 */
data class DateItemData(
    val date: Date,
    val dayTypeRelativeToToday: DayTypeRelativeToToday
)