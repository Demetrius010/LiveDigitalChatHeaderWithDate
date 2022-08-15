package space.livedigital.chat_sdk.ui.list.adapters.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import space.livedigital.chat_sdk.ui.list.view_holders.base.BaseViewHolder

/**
 * Базовый Adapter
 */
abstract class BaseAdapter<ItemClass, VH : BaseViewHolder>(
    protected val layoutInflater: LayoutInflater,
) : RecyclerView.Adapter<VH>() {

    protected var items: MutableList<ItemClass> = mutableListOf()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    protected abstract fun onBindViewHolder(holder: VH, item: ItemClass)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        onBindViewHolder(holder, item)
    }

    fun updateItems(items: List<ItemClass>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(items: List<ItemClass>) {
        val startPosition = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(startPosition, items.size)
    }

    /**
     * Выкинуть исплючение для несуществующего типа ViewHolder
     */
    protected fun throwUnknownViewHolderTypeException(): Nothing {
        throw IllegalArgumentException("Unknown ViewHolder Type!")
    }

    protected fun updateItem(item: ItemClass, position: Int, type: Int) {
        val startPosition: Int = items.size

        if (items.size <= position) {
            items.add(item)
            notifyItemInserted(startPosition)
            return
        }

        val typeForPosition = getItemViewType(position)

        if (type == typeForPosition) {
            items[position] = item
            notifyItemChanged(position, item)
            return
        }

        items.add(position, item)
        notifyItemInserted(position)
    }

    protected fun removeItemsByTypes(vararg types: Int) {
        items = (items.filterIndexed { index, _ ->
            val type = getItemViewType(index)
            !types.contains(type)
        }).toMutableList()

        notifyDataSetChanged()
    }

    protected fun getItem(position: Int) = items[position]

    protected fun updateItemsWithDiffUtil(
        items: List<ItemClass>,
        diffResult: DiffUtil.DiffResult
    ) {
        this.items = items.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    protected companion object {
        const val NOT_FOUND = -1
    }
}