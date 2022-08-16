package space.livedigital.chat_sdk.ui.domain.results

import space.livedigital.chat_sdk.ui.application.ApplicationThrowable
import space.livedigital.chat_sdk.ui.data.network.entities.ErrorData

/**
 * Ошибки полученные в результате выполнения асинхронной операции
 */
sealed class ExecutionError {
    class Expected(val data: ErrorData) : ExecutionError()
    class Failure(val throwable: ApplicationThrowable) : ExecutionError()
}