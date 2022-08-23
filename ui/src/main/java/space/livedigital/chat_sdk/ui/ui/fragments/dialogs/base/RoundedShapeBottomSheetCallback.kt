package space.livedigital.chat_sdk.ui.ui.fragments.dialogs.base

import android.view.View
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import space.livedigital.chat_sdk.ui.R

/**
 * [BottomSheetCallback] для исправления поведения скруглений при раскрытии Bottom Sheet
 * По гайдланам материал дизайна, Bottom Sheet в раскрытом состоянии не должен иметь скруглений
 * В библиотеке материал дизайна это поведение установлено насильно, но нам это поведение не нужно
 * поэтому мы вручную устанавливаем скругления при переходе Bottom Sheet в раскрытое состояние
 */
class RoundedShapeBottomSheetCallback : BottomSheetCallback() {
    override fun onStateChanged(bottomSheet: View, newState: Int) {
        val drawable = createMaterialShapeDrawable(bottomSheet)
        ViewCompat.setBackground(bottomSheet, drawable)
    }

    override fun onSlide(bottomSheet: View, slideOffset: Float) {
    }

    private fun createMaterialShapeDrawable(bottomSheet: View): MaterialShapeDrawable {
        val context = bottomSheet.context
        val shapeAppearanceModel = ShapeAppearanceModel.builder(
            context,
            0,
            R.style.RoundShapeAppearanceBottomSheetDialog
        ).build()
        val currentMaterialShapeDrawable = bottomSheet.background as MaterialShapeDrawable
        val newMaterialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
        newMaterialShapeDrawable.initializeElevationOverlay(context)
        newMaterialShapeDrawable.fillColor = currentMaterialShapeDrawable.fillColor
        newMaterialShapeDrawable.tintList = currentMaterialShapeDrawable.tintList
        newMaterialShapeDrawable.elevation = currentMaterialShapeDrawable.elevation
        newMaterialShapeDrawable.strokeWidth = currentMaterialShapeDrawable.strokeWidth
        newMaterialShapeDrawable.strokeColor = currentMaterialShapeDrawable.strokeColor
        return newMaterialShapeDrawable
    }
}