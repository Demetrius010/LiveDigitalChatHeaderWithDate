package space.livedigital.chat_sdk.ui.data.constants.list

/**
 * Id объектов отображения
 */
enum class ListItemIds {
    MESSAGE,
    DATE;

    fun value(): String {
        return name
    }
}