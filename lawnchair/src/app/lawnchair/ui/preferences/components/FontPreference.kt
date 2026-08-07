package app.qqlauncher.ui.preferences.components

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.qqlauncher.preferences.BasePreferenceManager
import app.qqlauncher.preferences.getAdapter
import app.qqlauncher.ui.preferences.LocalNavController
import app.qqlauncher.ui.preferences.components.layout.PreferenceTemplate
import app.qqlauncher.ui.preferences.navigation.GeneralFontSelection

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FontPreference(
    fontPref: BasePreferenceManager.FontPref,
    label: String,
    modifier: Modifier = Modifier,
) {
    val navController = LocalNavController.current

    PreferenceTemplate(
        title = { Text(text = label) },
        modifier = modifier,
        description = {
            val font = fontPref.getAdapter().state.value
            Text(
                text = font.fullDisplayName,
                fontFamily = font.composeFontFamily,
            )
        },
        onClick = { navController.navigate(route = GeneralFontSelection(fontPref.key)) },
    )
}
