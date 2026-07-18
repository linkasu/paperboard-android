package su.linka.linkapaperboard

import org.junit.Test
import kotlin.test.assertFailsWith

class ReleasePrivacyBoundaryTest {

    @Test
    fun telemetrySdksAreAbsentFromReleaseRuntime() {
        listOf(
            "com.google.firebase.crashlytics.FirebaseCrashlytics",
            "com.google.firebase.analytics.FirebaseAnalytics",
            "io.appmetrica.analytics.AppMetrica",
            "com.yandex.metrica.YandexMetrica"
        ).forEach { className ->
            assertFailsWith<ClassNotFoundException>(className) {
                Class.forName(className)
            }
        }
    }
}
