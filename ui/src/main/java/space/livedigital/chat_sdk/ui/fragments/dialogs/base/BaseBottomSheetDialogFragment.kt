package space.livedigital.chat_sdk.ui.fragments.dialogs.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Базовый [BottomSheetDialogFragment]
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    abstract fun getLayoutResource(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutResource(), container, false)
}