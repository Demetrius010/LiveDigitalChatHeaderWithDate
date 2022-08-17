package space.livedigital.chat_sdk.ui.ui.list.view_holders.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import space.livedigital.chat_sdk.ui.R
import space.livedigital.chat_sdk.ui.databinding.DateItemBinding
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.ListItem
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DateItemData
import space.livedigital.chat_sdk.ui.ui.data.entities.list_item.date.DayType
import space.livedigital.chat_sdk.ui.ui.list.view_holders.base.BaseViewHolder
import space.livedigital.chat_sdk.utils.ResourcesUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewHolder для загаловка с датой
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
        val currentLocale = Locale.getDefault()
        val dateFormatPattern = ResourcesUtils.getString(R.string.date_format_pattern)
        val monthNameAndDay: String =
            SimpleDateFormat(dateFormatPattern, currentLocale).format(data.date.time)
        monthNameAndDay.replaceFirstChar { it.uppercase() }

        val dateType = when (data.dayType) {
            DayType.TODAY -> ResourcesUtils.getString(R.string.today)
            DayType.YESTERDAY -> ResourcesUtils.getString(R.string.yesterday)
            DayType.DAY_BEFORE_YESTERDAY -> ResourcesUtils.getString(R.string.day_before_yesterday)
            else -> ""
        }

        binding.timeText.text =
            if (dateType.isNotEmpty()) {
                ResourcesUtils.getString(R.string.date_delimiter, dateType, monthNameAndDay)
            } else {
                monthNameAndDay
            }
    }
}