package space.livedigital.chat_sdk.ui.list.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import space.livedigital.chat_sdk.ui.list.view_holders.chat.DateViewHolder
import space.livedigital.chat_sdk.ui.list.view_holders.chat.MessageViewHolder
import space.livedigital.chat_sdk.utils.ResourcesUtils

/**
 * [ItemDecoration] для экрана чата
 */
class ChatItemDecoration : RecyclerView.ItemDecoration() {

    private var itemCount: Int? = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        itemCount = parent.adapter?.itemCount

        when (parent.getChildViewHolder(view)) {
            is MessageViewHolder -> setVerticalMargins(position, outRect, MESSAGE_MARGINS_IN_PX)
            is DateViewHolder -> setVerticalMargins(position, outRect, DATE_MARGINS_IN_PX)
        }
    }

    private fun setVerticalMargins(position: Int, outRect: Rect, marginInPx: Int) {
        if (position == 0) {
            outRect.top = BASE_MARGINS_IN_PX
        } else if (position == itemCount?.minus(1)) {
            outRect.bottom = BASE_MARGINS_IN_PX
        } else {
            outRect.top = marginInPx
            outRect.bottom = marginInPx
        }
    }

    private companion object {
        val BASE_MARGINS_IN_PX = ResourcesUtils.getPxByDp(20f)
        val MESSAGE_MARGINS_IN_PX = ResourcesUtils.getPxByDp(10f)
        val DATE_MARGINS_IN_PX = ResourcesUtils.getPxByDp(12f)
    }
}