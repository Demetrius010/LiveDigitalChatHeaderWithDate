package space.livedigital.chat_sdk.ui.data.constants.list

/**
 * Типы ListItem
 */
enum class ListItemTypes {
    // ListItem с переходом (подразумевается, что поле заполнятся)
    SELECTION,

    // ListItem с checkbox
    CHECKBOX,

    // ListItem с RadioButton
    RADIO_BUTTON,

    // ListItem c RadioGroup
    RADIO_GROUP,

    // ListItem с возможностью перехода (без выбора)
    LINK,

    // ListItem пока с любой кнопкой, возможно, кнопки будут делиться по типам
    BUTTON,

    // ListItem и заголовком блока на экране, возможно, будут разделения по типам
    HEADER,

    // ListItem со списком: например, галлерея фотографий
    LIST,

    // ListItem с тестовым сообщением
    TEXT,

    // ListItem с возможностью ввести значение
    INPUT_TEXT,

    // ListItem выбор значения без перехода (Spinner)
    INLINE_SELECTION,

    // ListItem с Switch элементом
    SWITCH
}