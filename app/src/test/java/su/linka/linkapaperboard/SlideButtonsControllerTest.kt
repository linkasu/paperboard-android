package su.linka.linkapaperboard

import org.junit.Test
import kotlin.test.assertEquals

class SlideButtonsControllerTest {

    @Test
    fun buildButtonText_singleCharacter() {
        val result = SlideButtonsController.buildButtonText("A", "⬅️")

        assertEquals("A\n⬅️", result)
    }

    @Test
    fun buildButtonText_truncatesLongText() {
        val result = SlideButtonsController.buildButtonText("abcdefg", "➡️")

        assertEquals("abc...efg\n➡️", result)
    }
}
