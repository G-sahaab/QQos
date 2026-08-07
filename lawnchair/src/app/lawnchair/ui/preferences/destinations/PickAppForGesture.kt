package app.qqlauncher.ui.preferences.destinations

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import app.qqlauncher.gestures.config.GestureHandlerConfig
import app.qqlauncher.gestures.handlers.OpenAppTarget
import app.qqlauncher.ui.preferences.LocalIsExpandedScreen
import app.qqlauncher.ui.preferences.components.AppItem
import app.qqlauncher.ui.preferences.components.AppItemPlaceholder
import app.qqlauncher.ui.preferences.components.layout.PreferenceLazyColumn
import app.qqlauncher.ui.preferences.components.layout.PreferenceScaffold
import app.qqlauncher.ui.preferences.components.layout.preferenceGroupItems
import app.qqlauncher.util.App
import app.qqlauncher.util.appsState
import app.qqlauncher.util.kotlinxJson
import com.android.launcher3.R

@Composable
fun PickAppForGesture() {
    val apps by appsState()
    val state = rememberLazyListState()

    val activity = LocalContext.current as Activity
    fun onSelectApp(app: App) {
        val config: GestureHandlerConfig = GestureHandlerConfig.OpenApp(
            appName = app.label,
            target = OpenAppTarget.App(app.key),
        )
        val configString = kotlinxJson.encodeToString(config)
        activity.setResult(Activity.RESULT_OK, Intent().putExtra("config", configString))
        activity.finish()
    }

    PreferenceScaffold(
        label = stringResource(id = R.string.pick_app_for_gesture),
        isExpandedScreen = LocalIsExpandedScreen.current,
    ) {
        Crossfade(targetState = apps.isNotEmpty(), label = "") { present ->
            if (present) {
                PreferenceLazyColumn(it, state = state) {
                    preferenceGroupItems(
                        items = apps,
                        isFirstChild = true,
                    ) { _, app ->
                        AppItem(
                            app = app,
                            onClick = { onSelectApp(app) },
                        )
                    }
                }
            } else {
                PreferenceLazyColumn(it, enabled = false) {
                    preferenceGroupItems(
                        count = 20,
                        isFirstChild = true,
                    ) {
                        AppItemPlaceholder()
                    }
                }
            }
        }
    }
}
