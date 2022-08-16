package space.livedigital.chat_sdk.ui.domain.results

/**
 * Ожидаемый результат асинхронной операций
 */
sealed class ExecutionResult<out Data> {
    class Success<Data>(val data: Data) : ExecutionResult<Data>()
    class Error(val error: ExecutionError) : ExecutionResult<Nothing>()
}