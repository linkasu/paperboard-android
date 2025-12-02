package su.linka.linkapaperboard

import android.inputmethodservice.InputMethodService
import android.view.View

class PaperboardIME : InputMethodService() {

    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(R.layout.keyboard_main, null)
        val preferences = Cookie.from(applicationContext)
        GridController(
            view,
            preferences,
            uppercaseProvider = { preferences.getUppercaseChecked() }
        ) { key ->
            currentInputConnection?.commitText(key, 1)
        }
        view.findViewById<View>(R.id.keyboard_backspace).setOnClickListener {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }
        return view
    }
}
