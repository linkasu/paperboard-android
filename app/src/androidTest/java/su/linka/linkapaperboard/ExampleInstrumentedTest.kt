package su.linka.linkapaperboard

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("su.linka.linkapaperboard", appContext.packageName)
    }
}
