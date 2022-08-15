package space.livedigital.chat_sdk.ui.data.entities.list_item

import space.livedigital.chat_sdk.ui.data.constants.list.ListItemTypes

/**
 * Универсальный объект для отображения элементов в списке
 */
data class ListItem(
    var type: ListItemTypes? = null,
    var id: String? = null,
    var data: Any,
    var settings: Settings = Settings(),
    var errors: Errors = Errors()
)

/**
 * Ошибка объекта [ListItem]
 */
data class Errors(var message: String? = null)

/**
 * Настройки объекта [ListItem]
 */
data class Settings(var marginTopInDp: Int? = null)