/*
 * Copyright 2021, QQ Launcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.qqlauncher.ui.preferences.components.colorpreference

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.Preferences
import app.qqlauncher.preferences.PreferenceAdapter
import app.qqlauncher.preferences.getAdapter
import app.qqlauncher.theme.color.ColorOption
import app.qqlauncher.ui.preferences.LocalNavController
import app.qqlauncher.ui.preferences.components.layout.PreferenceTemplate
import app.qqlauncher.ui.preferences.navigation.ColorSelection as ColorSelectionRoute
import app.qqlauncher.ui.theme.QQ LauncherTheme
import app.qqlauncher.ui.util.preview.PreferenceGroupPreviewContainer
import app.qqlauncher.ui.util.preview.PreviewQQ Launcher
import com.patrykmichalik.opto.domain.Preference

/**
 * A custom implementation of [PreferenceTemplate] for [ColorOption] preferences.
 *
 * @see ColorSelection
 */
@Composable
fun ColorPreference(
    preference: Preference<ColorOption, String, Preferences.Key<String>>,
    modifier: Modifier = Modifier,
) {
    val modelList = ColorPreferenceModelList.INSTANCE.get(LocalContext.current)
    val model = modelList[preference.key.name]
    val adapter: PreferenceAdapter<ColorOption> = model.prefObject.getAdapter()
    val navController = LocalNavController.current
    ColorPreference(
        label = stringResource(id = model.labelRes),
        selectedColor = adapter.state.value,
        onClick = { navController.navigate(route = ColorSelectionRoute(model.prefObject.key.name)) },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ColorPreference(
    label: String,
    selectedColor: ColorOption,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PreferenceTemplate(
        title = { Text(text = label) },
        modifier = modifier,
        description = {
            Text(text = selectedColor.colorPreferenceEntry.label())
        },
        endWidget = {
            ColorDot(selectedColor.colorPreferenceEntry)
        },
        onClick = onClick,
    )
}

@PreviewQQ Launcher
@Composable
private fun ColorPreferencePreview() {
    QQ LauncherTheme {
        PreferenceGroupPreviewContainer {
            ColorPreference(
                label = "Accent Color",
                selectedColor = ColorOption.QQ LauncherBlue,
                onClick = {},
            )
        }
    }
}
