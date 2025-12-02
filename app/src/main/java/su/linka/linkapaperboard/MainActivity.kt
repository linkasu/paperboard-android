package su.linka.linkapaperboard

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: Cookie
    private lateinit var textController: TextViewController
    private lateinit var gridController: GridController
    private lateinit var controlButtonsController: ControlButtonsController
    private lateinit var ttsManager: TtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val view: View = window.decorView
        view.isFocusable = true
        view.isFocusableInTouchMode = true

        val configBuilder = YandexMetricaConfig.newConfigBuilder(getString(R.string.yandex_metric_config_id))
        YandexMetrica.activate(applicationContext, configBuilder.build())
        YandexMetrica.enableActivityAutoTracking(application)

        preferences = Cookie.from(applicationContext)
        ttsManager = TtsManager(applicationContext)
        textController = TextViewController(findViewById(R.id.textView))
        gridController = GridController(
            view,
            preferences,
            uppercaseProvider = { preferences.getUppercaseChecked() }
        ) { symbol ->
            textController.write(symbol)
        }
        controlButtonsController = ControlButtonsController(
            backspace = findViewById(R.id.button_backspace),
            clean = findViewById(R.id.button_clean),
            say = findViewById(R.id.button_say),
            textController = textController,
            ttsManager = ttsManager
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.action_uppercase)
        item.isChecked = preferences.getUppercaseChecked()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change_grid_size -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.change_grid_size)
                val input = SeekBar(this).apply {
                    max = 3
                    progress = gridController.size - 1
                }
                builder.setView(input)
                builder.setPositiveButton(R.string.ok) { _, _ ->
                    val size = input.progress + 1
                    gridController.setGridSize(size)
                }
                builder.show()
                return true
            }

            R.id.action_uppercase -> {
                val checked = !item.isChecked
                preferences.setUppercaseChecked(checked)
                item.isChecked = checked
                gridController.refreshUppercase()
                return true
            }

            R.id.action_install_as_system_keyboard -> {
                startActivityForResult(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(window.decorView, InputMethodManager.SHOW_FORCED)
            imm?.showInputMethodPicker()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        ttsManager.shutdown()
        super.onDestroy()
    }
}
