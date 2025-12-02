package su.linka.linkapaperboard

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting

class Cookie private constructor(private val sharedPref: SharedPreferences) {

    val gridSize: Int
        get() = sharedPref.getInt(GRID_SIZE_ID, DEF_VALUE)

    fun setGridSize(gridSize: Int) {
        sharedPref.edit()
            .putInt(GRID_SIZE_ID, gridSize)
            .apply()
    }

    fun setUppercaseChecked(checked: Boolean) {
        sharedPref.edit()
            .putBoolean(GRID_UPPERCASE_ID, checked)
            .apply()
    }

    fun getUppercaseChecked(): Boolean = sharedPref.getBoolean(GRID_UPPERCASE_ID, true)

    companion object {
        private const val STORAGE_NAME = "storage"
        private const val DEF_VALUE = 3
        private const val GRID_SIZE_ID = "gridsize"
        private const val GRID_UPPERCASE_ID = "griduppercase"

        fun from(context: Context): Cookie {
            val appContext = context.applicationContext
            val prefs = appContext.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
            return Cookie(prefs)
        }

        @VisibleForTesting
        internal fun resetForTests(testContext: Context) {
            testContext.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit()
        }
    }
}
