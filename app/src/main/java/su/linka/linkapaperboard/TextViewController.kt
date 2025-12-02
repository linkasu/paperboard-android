package su.linka.linkapaperboard

import android.widget.TextView

class TextViewController(private val textView: TextView) {

    fun write(text: String) {
        textView.append(if (text == "_") " " else text)
    }

    fun backspace() {
        val value = text
        if (value.isEmpty()) return
        textView.text = value.substring(0, value.length - 1)
    }

    fun clean() {
        textView.text = ""
    }

    val text: String
        get() = textView.text.toString()
}
