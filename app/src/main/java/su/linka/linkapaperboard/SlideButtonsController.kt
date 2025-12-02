package su.linka.linkapaperboard

import androidx.appcompat.widget.AppCompatButton
import android.view.View

class SlideButtonsController(view: View) {

    private val leftBtn: AppCompatButton = view.findViewById(R.id.left_btn)
    private val rightBtn: AppCompatButton = view.findViewById(R.id.right_btn)

    fun setTextForLeftBtn(text: String) {
        setTextForButton(leftBtn, text, "⬅️")
    }

    fun setTextForRightBtn(text: String) {
        setTextForButton(rightBtn, text, "➡️")
    }

    private fun setTextForButton(button: AppCompatButton, text: String?, arrowSymbol: String) {
        if (text.isNullOrEmpty()) {
            return
        }
        button.text = buildButtonText(text, arrowSymbol)
    }

    companion object {
        internal fun buildButtonText(text: String, arrowSymbol: String): String {
            val formatted = if (text.length == 1) {
                text
            } else {
                "${text.take(3)}...${text.takeLast(3)}"
            }
            return "$formatted\n$arrowSymbol"
        }
    }
}
