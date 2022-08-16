package space.livedigital.chat_sdk.ui.ui.data.constants.list

/**
 * Id объектов [ListItem]
 */
enum class ListItemIds {
    MESSAGE,
    DATE;

    fun value(): String {
        return name
    }
}