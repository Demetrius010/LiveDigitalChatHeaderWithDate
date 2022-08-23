package space.livedigital.chat_sdk.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Класс для работы с датами
 */
object DateUtils {

    private const val ADULTHOOD_AGE_IN_YEARS = 18

    // По закону совершеннолетие наступает на следующий день после дня рождения
    private const val DAY_AFTER_BEGINNING_OF_ADULTHOOD = 1

    /**
     * Возвращает дату из форматированной строки
     *
     * @param millis       дата в миллисекундах
     * @param sourceFormat формат строки
     * @return дата
     */
    fun getLocalFormattedSecondsString(
        millis: Long,
        sourceFormat: DateFormat
    ): String? {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        var formattedDate: String? = null
        try {
            val dateFormat = SimpleDateFormat(sourceFormat.format, Locale.getDefault())
            formattedDate = dateFormat.format(calendar.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return formattedDate
    }

    /**
     * Возвращает отформатированную дату, с учетом [Locale] установленной по умолчанию
     *
     * @param date         дата
     * @param sourceFormat формат строки
     * @return отформатированная дата
     */
    fun getFormattedDateString(
        date: Date?,
        sourceFormat: DateFormat
    ): String? {
        if (date == null) {
            return null
        }

        var formattedDate: String? = null
        try {
            val dateFormat = SimpleDateFormat(sourceFormat.format, Locale.getDefault())
            formattedDate = dateFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return formattedDate
    }

    /**
     * Возвращает дату по UTC из форматированной строки
     *
     * @param source       форматированная строка
     * @param sourceFormat формат строки
     * @return дата
     */
    fun getUTCDateFromFormattedString(
        source: String?,
        sourceFormat: DateFormat
    ): Date? {
        if (source == null) {
            return null
        }

        var date: Date? = null
        try {
            val dateFormat = SimpleDateFormat(sourceFormat.format, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            date = dateFormat.parse(source)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }

    /**
     * Возвращает текущую дату и время в миллисекундах
     */
    fun getCurrentTimeInMilliseconds(): Long {
        return Calendar.getInstance().timeInMillis
    }

    /**
     * Проверяет являются ли передаваемые даты одним и тем же днём или нет
     *
     * @param date1 первая проверяемая [дата][Date]
     * @param date2 вторая проверяемая [дата][Date]
     * @return true - если в датах установлен один и тот же день, false - в ином случае
     */
    fun isTheSameDay(date1: Date, date2: Date): Boolean {
        val calendar1 = Calendar.getInstance().apply { time = date1 }
        val calendar2 = Calendar.getInstance().apply { time = date2 }
        return isTheSameDay(calendar1, calendar2)
    }

    /**
     * Получить дату совершеннолетия в миллисекундах
     */
    fun getDateOfAdulthoodInMilliseconds(): Long {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.YEAR, -ADULTHOOD_AGE_IN_YEARS)
            add(Calendar.DATE, -DAY_AFTER_BEGINNING_OF_ADULTHOOD)
        }
        return calendar.time.time
    }

    /**
     * Проверяет являются ли передаваемые даты одним и тем же днём или нет
     *
     * @param calendar1 [Calendar] с первой проверяемой [датой][Date]
     * @param calendar2 [Calendar] со второй проверяемой [датой][Date]
     * @return true - если в датах установлен один и тот же день, false - в ином случае
     */
    private fun isTheSameDay(calendar1: Calendar, calendar2: Calendar): Boolean {
        if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR)) {
            return false
        }
        if (calendar1.get(Calendar.DAY_OF_YEAR) != calendar2.get(Calendar.DAY_OF_YEAR)) {
            return false
        }
        return true
    }

    /**
     * Проверяет является ли переданная дата текущим днем в UTC.
     * @param date проверяемая дата
     * @return truе - если дата текущая.
     */
    fun isTodayInUTC(date: Date): Boolean {
        return isTheSameDay(date, Calendar.getInstance(TimeZone.getTimeZone("UTC")).time)
    }

    /**
     * Проверяет является ли переданная дата вчерашним днем в UTC.
     * @param date проверяемая дата
     * @return truе - если дата является вчерашним днем.
     */
    fun isYesterdayInUTC(date: Date): Boolean {
        val calendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar1.add(Calendar.DAY_OF_YEAR, -1)

        val calendar2 = Calendar.getInstance().apply { time = date }

        return isTheSameDay(calendar1, calendar2)
    }

    /**
     * Проверяет является ли переданная дата позавчерашним днем в UTC .
     * @param date проверяемая дата
     * @return truе - если дата является позавчерашним днем.
     */
    fun isDayBeforeYesterdayInUTC(date: Date): Boolean {
        val calendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar1.add(Calendar.DAY_OF_YEAR, -2)

        val calendar2 = Calendar.getInstance().apply { time = date }

        return isTheSameDay(calendar1, calendar2)
    }

    /**
     * Возвращет дату без времени
     * @param date изменяемая дата
     * @return дата, время у которой установлено в полночь
     */
    fun getDateWithoutTime(date: Date): Date {
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    enum class DateFormat(var format: String) {
        HH_MM("h:mm"),
        MM_SS("m:ss"),
        ISO_8601_WITH_TIME_ZONE("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
        HH_MM_DD_MMMM_YYYY("HH:mm, dd MMMM yyyy"),
        YYYY_MM_DD("yyyy-MM-dd"),
        D_MMMM_YYYY("d MMMM yyyy")
    }
}