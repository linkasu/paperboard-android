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

    val privacyNoticeAcknowledged: Boolean
        get() = sharedPref.getBoolean(PRIVACY_NOTICE_ACKNOWLEDGED_ID, false)

    fun acknowledgePrivacyNotice() {
        sharedPref.edit()
            .putBoolean(PRIVACY_NOTICE_ACKNOWLEDGED_ID, true)
            .apply()
    }

    companion object {
        private const val STORAGE_NAME = "storage"
        private const val DEF_VALUE = 3
        private const val GRID_SIZE_ID = "gridsize"
        private const val GRID_UPPERCASE_ID = "griduppercase"
        private const val PRIVACY_NOTICE_ACKNOWLEDGED_ID = "privacy_notice_acknowledged"

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
