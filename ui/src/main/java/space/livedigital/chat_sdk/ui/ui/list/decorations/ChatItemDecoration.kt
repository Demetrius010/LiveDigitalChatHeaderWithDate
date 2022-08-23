package space.livedigital.chat_sdk.ui.ui.list.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import space.livedigital.chat_sdk.ui.ui.list.view_holders.chat.DateViewHolder
import space.livedigital.chat_sdk.ui.ui.list.view_holders.chat.MessageViewHolder
import space.livedigital.chat_sdk.utils.ResourcesUtils

/**
 * [ItemDecoration] для экрана чата
 */
class ChatItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = getItemPosition(view, parent)

        when (parent.getChildViewHolder(view)) {
            is MessageViewHolder -> setMarginVertical(itemPosition, outRect, MESSAGE_MARGIN_IN_PX)
            is DateViewHolder -> setMarginVertical(itemPosition, outRect, DATE_MARGIN_IN_PX)
        }
    }

    private fun setMarginVertical(itemPosition: ItemPosition, outRect: Rect, marginInPx: Int) {
        when (itemPosition) {
            ItemPosition.FIRST -> {
                outRect.top = BASE_T0P_MARGIN_IN_PX
                outRect.bottom = marginInPx
            }
            ItemPosition.LAST -> {
                outRect.top = marginInPx
                outRect.bottom = BASE_BOTTOM_MARGIN_IN_PX
            }
            ItemPosition.MIDDLE -> {
                outRect.top = marginInPx
                outRect.bottom = marginInPx
            }
        }
    }

    private fun getItemPosition(itemView: View, parent: RecyclerView): ItemPosition {
        val position = parent.getChildAdapterPosition(itemView)
        val lastPosition = parent.adapter?.itemCount?.minus(1)

        return when (position) {
            0 -> ItemPosition.FIRST
            lastPosition -> ItemPosition.LAST
            else -> ItemPosition.MIDDLE
        }
    }

    private sealed class ItemPosition {
        object FIRST : ItemPosition()
        object MIDDLE : ItemPosition()
        object LAST : ItemPosition()
    }

    private companion object {
        val BASE_T0P_MARGIN_IN_PX = ResourcesUtils.getPxByDp(10f)
        val BASE_BOTTOM_MARGIN_IN_PX = ResourcesUtils.getPxByDp(20f)
        val MESSAGE_MARGIN_IN_PX = ResourcesUtils.getPxByDp(10f)
        val DATE_MARGIN_IN_PX = ResourcesUtils.getPxByDp(12f)
    }
}