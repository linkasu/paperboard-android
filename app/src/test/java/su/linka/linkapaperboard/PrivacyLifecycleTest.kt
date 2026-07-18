package su.linka.linkapaperboard

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.GridView
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowAlertDialog
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class PrivacyLifecycleTest {

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
    fun coldStartNoticeCanBeDeferredWithoutBlockingTts() {
        val activityController = Robolectric.buildActivity(MainActivity::class.java).setup()
        val activity = activityController.get()
        val dialog = ShadowAlertDialog.getLatestAlertDialog() as AlertDialog

        assertTrue(dialog.isShowing)
        assertNotNull(dialog.getButton(DialogInterface.BUTTON_NEGATIVE))
        dialog.cancel()

        assertFalse(dialog.isShowing)
        assertFalse(Cookie.from(context).privacyNoticeAcknowledged)
        assertTrue(activity.findViewById<View>(R.id.button_say).isEnabled)
        activity.findViewById<View>(R.id.button_say).performClick()
        activityController.pause().stop().destroy()
    }

    @Test
    fun imeInputViewStartsWhilePrivacyNoticeIsPending() {
        val serviceController = Robolectric.buildService(PaperboardIME::class.java).create()
        val service = serviceController.get()

        val inputView = service.onCreateInputView()

        assertNotNull(inputView)
        assertFalse(Cookie.from(context).privacyNoticeAcknowledged)
        val grid = inputView.findViewById<GridView>(R.id.main_grid)
        assertTrue(grid.isEnabled)
        assertTrue(grid.adapter.count > 0)
        requireNotNull(grid.onItemClickListener).onItemClick(grid, null, 0, 0)
        inputView.findViewById<View>(R.id.keyboard_backspace).performClick()
        serviceController.destroy()
    }
}
