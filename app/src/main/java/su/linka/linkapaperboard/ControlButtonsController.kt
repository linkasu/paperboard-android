package su.linka.linkapaperboard

import android.widget.Button

class ControlButtonsController(
    backspace: Button,
    clean: Button,
    say: Button,
    private val textController: TextViewController,
    private val ttsManager: TtsManager
) {

    init {
        backspace.setOnClickListener { textController.backspace() }
        clean.setOnClickListener { textController.clean() }
        say.setOnClickListener { ttsManager.speak(textController.text) }
    }
}
