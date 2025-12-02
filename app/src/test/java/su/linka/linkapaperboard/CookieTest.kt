package su.linka.linkapaperboard

import android.content.Context
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class CookieTest {

    private val context: Context = RuntimeEnvironment.getApplication()

    @Before
    fun setUp() {
        Cookie.resetForTests(context)
    }

    @After
    fun tearDown() {
        Cookie.resetForTests(context)
    }

    @Test
    fun gridSize_persists() {
        val cookie = Cookie.from(context)
        assertEquals(3, cookie.gridSize)

        cookie.setGridSize(4)

        assertEquals(4, Cookie.from(context).gridSize)
    }

    @Test
    fun uppercase_default_and_toggle() {
        val cookie = Cookie.from(context)
        assertTrue(cookie.getUppercaseChecked())

        cookie.setUppercaseChecked(false)

        assertEquals(false, Cookie.from(context).getUppercaseChecked())
    }
}
