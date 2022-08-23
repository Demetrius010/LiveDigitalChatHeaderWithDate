package space.livedigital.chat_sdk.ui.ui.list.view_holders.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.databinding.DateItemBinding
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DateItemData
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayTypeRelativeToToday
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayTypeRelativeToToday.*
import space.livedigital.chat_sdk.ui.ui.list.view_holders.base.BaseViewHolder
import space.livedigital.chat_sdk.utils.DateUtils
import space.livedigital.chat_sdk.utils.ResourcesUtils
import java.util.*

/**
 * ViewHolder для заголовка с датой
 */
class DateViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(inflater, parent, R.layout.date_item) {

    private val binding = DateItemBinding.bind(itemView)

    fun bind(listItem: ListItem) {
        val itemDate = listItem.data as DateItemData
        showDate(itemDate)
    }

    private fun showDate(data: DateItemData) {
        getTimeText(data)?.let { binding.timeText.text = it }
    }

    private fun getTimeText(data: DateItemData): String? {
        val dayTypeRelativeToTodayString =
            getDayTypeRelativeToTodayString(data.dayTypeRelativeToToday)
        val monthNameAndDayString = getMonthNameAndDayString(data.date)

        return if (dayTypeRelativeToTodayString.isNotEmpty()) {
            ResourcesUtils.getString(
                R.string.date_delimiter,
                dayTypeRelativeToTodayString,
                monthNameAndDayString
            )
        } else {
            monthNameAndDayString
        }
    }

    private fun getDayTypeRelativeToTodayString(dayTypeRelativeToToday: DayTypeRelativeToToday) =
        when (dayTypeRelativeToToday) {
            TODAY -> ResourcesUtils.getString(R.string.today)
            YESTERDAY -> ResourcesUtils.getString(R.string.yesterday)
            DAY_BEFORE_YESTERDAY -> ResourcesUtils.getString(R.string.day_before_yesterday)
            else -> ""
        }


    private fun getMonthNameAndDayString(date: Date): String? {
        val dateFormatPattern = ResourcesUtils.getString(R.string.date_format_pattern)
        val monthNameAndDay = DateUtils.getFormattedDateString(date, dateFormatPattern)
        monthNameAndDay?.replaceFirstChar { it.uppercase() }
        return monthNameAndDay
    }
}