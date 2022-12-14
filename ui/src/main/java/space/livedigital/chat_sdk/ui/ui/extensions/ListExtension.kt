package space.livedigital.chat_sdk.ui.ui.extensions

import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import space.livedigital.chat_sdk.ui.ui.list.view_holders.base.BaseViewHolder

/**
 * Расширение для инициализации [RecyclerView] и задания параметров
 */
class ListExtension(private var list: RecyclerView?) {

    /**
     * Необходимость блокировать список
     * true - список заблокирован
     */
    private var shouldLockList = false

    init {
        setLayoutManager()
        addOnItemTouchListener()
    }

    /**
     * Задать [Adapter]
     */
    fun setAdapter(adapter: Adapter<BaseViewHolder>) {
        list?.adapter = adapter
    }

    /**
     * Добавить [ItemDecoration] списка
     */
    fun addItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        list?.addItemDecoration(itemDecoration)
    }

    /**
     * Удалить [ItemDecoration] списка
     */
    fun removeItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        list?.removeItemDecoration(itemDecoration)
    }

    /**
     * Заблокировать список
     */
    fun lock() {
        shouldLockList = true
    }

    /**
     * Разблокировать список
     */
    fun unlock() {
        shouldLockList = false
    }

    /**
     * Задать [LayoutManager]
     * По умолчанию задается [LinearLayoutManager]
     */
    fun setLayoutManager(layoutManager: LayoutManager = getDefaultLayoutManager()) {
        list?.layoutManager = layoutManager
    }

    /**
     * Получить [LayoutManager] по умолчанию
     */
    private fun getDefaultLayoutManager(): LayoutManager {
        return LinearLayoutManager(
            null,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    /**
     * Добавление слушателя для возможности блокировки списка
     */
    private fun addOnItemTouchListener() {
        list?.addOnItemTouchListener(object : SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return shouldLockList
            }
        })
    }
}