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
            is MessageViewHolder -> setMarginVertical(position, outRect, MESSAGE_MARGIN_IN_PX)
            is DateViewHolder -> setMarginVertical(position, outRect, DATE_MARGIN_IN_PX)
        }
    }

    private fun setMarginVertical(position: Int, outRect: Rect, marginInPx: Int) {
        if (position == 0) {
            outRect.top = BASE_T0P_MARGIN_IN_PX
            outRect.bottom = marginInPx
        } else if (position == itemCount?.minus(1)) {
            outRect.top = marginInPx
            outRect.bottom = BASE_BOTTOM_MARGIN_IN_PX
        } else {
            outRect.top = marginInPx
            outRect.bottom = marginInPx
        }
    }

    private companion object {
        val BASE_T0P_MARGIN_IN_PX = ResourcesUtils.getPxByDp(10f)
        val BASE_BOTTOM_MARGIN_IN_PX = ResourcesUtils.getPxByDp(20f)
        val MESSAGE_MARGIN_IN_PX = ResourcesUtils.getPxByDp(10f)
        val DATE_MARGIN_IN_PX = ResourcesUtils.getPxByDp(12f)
    }
}