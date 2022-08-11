package space.livedigital.chat_sdk.ui.fragments.dialogs.base

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import space.livedigital.chat_sdk.ui.R

/**
 * Базовый [BottomSheetDialogFragment] со скругленными краями
 */
abstract class BaseRoundedBottomSheetDialogFragment : BaseBottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        val callBack = RoundedShapeBottomSheetCallback()
        (dialog as BottomSheetDialog).behavior.addBottomSheetCallback(callBack)

        return dialog
    }

    override fun getTheme() = R.style.AppTheme_BottomSheetDialogTheme
}