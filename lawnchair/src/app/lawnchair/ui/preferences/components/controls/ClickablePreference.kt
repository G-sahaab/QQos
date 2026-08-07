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

package app.qqlauncher.ui.preferences.components.controls

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.qqlauncher.ui.ModalBottomSheetContent
import app.qqlauncher.ui.preferences.components.layout.PreferenceTemplate
import app.qqlauncher.ui.theme.QQ LauncherTheme
import app.qqlauncher.ui.util.bottomSheetHandler
import app.qqlauncher.ui.util.preview.PreferenceGroupPreviewContainer
import app.qqlauncher.ui.util.preview.PreviewQQ Launcher

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ClickablePreference(
    label: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    confirmationText: String? = null,
    colors: ListItemColors = ListItemDefaults.segmentedColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
    ),
    onClick: () -> Unit,
) {
    val bottomSheetHandler = bottomSheetHandler
    PreferenceTemplate(
        title = { Text(text = label) },
        modifier = modifier,
        description = subtitle?.let { { Text(text = it) } },
        onClick = {
            if (confirmationText != null) {
                bottomSheetHandler.show {
                    PreferenceClickConfirmation(
                        title = label,
                        text = confirmationText,
                        onDismissRequest = { bottomSheetHandler.hide() },
                        onConfirm = onClick,
                    )
                }
            } else {
                onClick()
            }
        },
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PreferenceClickConfirmation(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheetContent(
        title = { Text(text = title) },
        text = { Text(text = text) },
        buttons = {
            OutlinedButton(
                onClick = onDismissRequest,
                shapes = ButtonDefaults.shapes(),
            ) {
                Text(text = stringResource(id = android.R.string.cancel))
            }
            Spacer(modifier = Modifier.requiredWidth(8.dp))
            Button(
                onClick = {
                    onDismissRequest()
                    onConfirm()
                },
                shapes = ButtonDefaults.shapes(),
            ) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        },
        modifier = modifier,
    )
}

@PreviewQQ Launcher
@Composable
private fun ClickablePreferencePreview() {
    QQ LauncherTheme {
        PreferenceGroupPreviewContainer {
            ClickablePreference(
                label = "Label",
                subtitle = "Subtitle",
                onClick = {},
            )
        }
    }
}
