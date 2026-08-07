package app.qqlauncher.ui.preferences.components

import android.R as AndroidR
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ResultReceiver
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.qqlauncher.gestures.config.GestureHandlerConfig
import app.qqlauncher.gestures.config.GestureHandlerOption
import app.qqlauncher.gestures.config.buildConfigFrom
import app.qqlauncher.gestures.config.filterGestureHandlerOptions
import app.qqlauncher.gestures.type.GestureType
import app.qqlauncher.gestures.ui.QQ LauncherShortcutActivity
import app.qqlauncher.preferences.PreferenceAdapter
import app.qqlauncher.preferences.getAdapter
import app.qqlauncher.preferences2.preferenceManager2
import app.qqlauncher.ui.ModalBottomSheetContent
import app.qqlauncher.ui.preferences.components.layout.PreferenceDivider
import app.qqlauncher.ui.preferences.components.layout.PreferenceTemplate
import app.qqlauncher.ui.util.LocalBottomSheetHandler
import com.android.launcher3.util.ComponentKey
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun GestureHandlerPreference(
    adapter: PreferenceAdapter<GestureHandlerConfig>,
    label: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val bottomSheetHandler = LocalBottomSheetHandler.current
    val prefs2 = preferenceManager2()

    val currentConfig = adapter.state.value

    fun onSelect(option: GestureHandlerOption) {
        scope.launch {
            val config = option.buildConfigFrom(context) ?: return@launch
            adapter.onChange(config)
        }
    }

    val newOptions =
        filterGestureHandlerOptions(deckLayoutEnabled = prefs2.deckLayout.getAdapter().state.value)

    PreferenceTemplate(
        title = { Text(text = label) },
        modifier = modifier,
        description = { Text(text = currentConfig.getLabel(context)) },
        onClick = {
            bottomSheetHandler.show {
                ModalBottomSheetContent(
                    title = { Text(label) },
                    buttons = {
                        OutlinedButton(
                            onClick = { bottomSheetHandler.hide() },
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(text = stringResource(id = AndroidR.string.cancel))
                        }
                    },
                ) {
                    LazyColumn {
                        itemsIndexed(newOptions) { index, option ->
                            if (index > 0) {
                                PreferenceDivider(startIndent = 40.dp)
                            }
                            val selected = currentConfig::class.java == option.configClass
                            PreferenceTemplate(
                                title = { Text(option.getLabel(context)) },
                                onClick = {
                                    bottomSheetHandler.hide()
                                    onSelect(option)
                                },
                                startWidget = {
                                    RadioButton(
                                        selected = selected,
                                        onClick = null,
                                    )
                                },
                                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            )
                        }
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppGesturePreference(
    cmp: ComponentKey,
    gestureType: GestureType,
    label: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val prefs = preferenceManager2()

    val currentConfig by produceState<GestureHandlerConfig>(initialValue = GestureHandlerConfig.NoOp) {
        prefs.getGestureForApp(cmp, gestureType).collect { value = it }
    }

    val resultReceiver = remember {
        object : ResultReceiver(Handler(Looper.getMainLooper())) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                if (resultCode == android.app.Activity.RESULT_OK) {
                    val handlerString = resultData?.getString(QQ LauncherShortcutActivity.EXTRA_HANDLER)
                    if (handlerString != null) {
                        val config = GestureHandlerConfig.fromString(handlerString)
                        scope.launch {
                            prefs.setGestureForApp(cmp, gestureType, config)
                        }
                    }
                }
            }
        }
    }

    PreferenceTemplate(
        title = { Text(text = label) },
        description = { Text(text = currentConfig.getLabel(context)) },
        modifier = modifier.fillMaxWidth(),
        onClick = {
            val intent = Intent(context, QQ LauncherShortcutActivity::class.java).apply {
                putExtra(QQ LauncherShortcutActivity.EXTRA_RESULT_RECEIVER, resultReceiver)
            }
            context.startActivity(intent)
        },
    )
}
