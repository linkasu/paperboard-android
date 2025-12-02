package su.linka.linkapaperboard

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.speech.tts.TextToSpeech
import com.yandex.metrica.YandexMetrica
import java.util.Locale

class TtsManager(private val appContext: Context) {

    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(appContext) { _ ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val available = tts?.availableLanguages
                if (available != null && !available.contains(Locale.getDefault())) {
                    showLanguageDialog()
                }
            }
            tts?.language = Locale.getDefault()
        }
    }

    private fun showLanguageDialog() {
        AlertDialog.Builder(appContext)
            .setMessage(R.string.install_tts)
            .setTitle(R.string.lang_doesnt_support)
            .setPositiveButton(R.string.ok) { _, _ ->
                val appPackageName = "com.google.android.tts"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                try {
                    appContext.startActivity(intent)
                } catch (anfe: android.content.ActivityNotFoundException) {
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName"))
                    webIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    appContext.startActivity(webIntent)
                }
            }
            .create()
            .show()
    }

    fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utteranceId")
        } else {
            @Suppress("DEPRECATION")
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
        YandexMetrica.reportEvent("speak", "{\"text\":\"$text\"}")
    }

    fun shutdown() {
        tts?.shutdown()
        tts = null
    }
}
