# Repository Instructions

## Project Shape
- Single-module Android app: `settings.gradle` only includes `:app`.
- Kotlin/Android sources live under `app/src/main/java/su/linka/linkapaperboard`; package and namespace are `su.linka.linkapaperboard`.
- Main entrypoints are `MainActivity` for the launcher UI and `PaperboardIME` for the input method service declared in `app/src/main/AndroidManifest.xml`.
- `MainActivity` and `PaperboardIME` both wire `GridController`; shared key grid UI is in `app/src/main/res/layout/keys.xml`.

## Toolchain
- Use the checked-in Gradle wrapper (`./gradlew`), Gradle 8.13, Android Gradle Plugin 8.13.1, Kotlin 1.9.0.
- Java 17 is required; CI uses Temurin 17.
- Android config: `compileSdk 36`, `targetSdk 35`, `minSdk 16`.

## Verification Commands
- Full local JVM unit tests: `./gradlew testDebugUnitTest`.
- One unit test class: `./gradlew testDebugUnitTest --tests su.linka.linkapaperboard.CookieTest`.
- Android lint for debug: `./gradlew lintDebug`.
- CI release build command: `./gradlew clean bundleRelease`.
- Instrumented tests are under `app/src/androidTest`; run with `./gradlew connectedDebugAndroidTest` only when an emulator/device is available.

## Build And Signing Notes
- Release signing is conditional in `app/build.gradle`: set `ANDROID_KEYSTORE`, `ANDROID_KEYSTORE_PASSWORD`, `ANDROID_KEY_ALIAS`, and `ANDROID_KEY_PASSWORD` only when a signed release artifact is needed.
- GitHub Actions decodes `ANDROID_KEYSTORE_BASE64` to `app/release.keystore` before `clean bundleRelease`; local unsigned release bundle builds can run without those env vars.
- `app/google-services.json` is tracked and the build applies Google Services plus Firebase Crashlytics plugins; do not remove them as "unused" without checking generated resources/tasks.

## Testing Notes
- Unit tests can use Android resources: `unitTests.includeAndroidResources = true`.
- Robolectric is configured via `app/src/test/resources/robolectric.properties` with `sdk=34`.
- `Cookie` persists to SharedPreferences; tests that touch it should call `Cookie.resetForTests(context)` in setup/teardown like `CookieTest`.

## App-Specific Gotchas
- The selectable alphabet comes from localized `@string/alphabet`; English and Russian strings intentionally differ.
- The space key is represented internally as `␣` and converted to a real space in `GridController.onItemClick`.
- Grid size is user-controlled through `Cookie.gridSize`; `GridController.setGridSize` resets pagination to page 0.
