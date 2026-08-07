package app.qqlauncher.qsb.providers

import app.qqlauncher.animateToAllApps
import app.qqlauncher.preferences.PreferenceManager
import app.qqlauncher.qsb.ThemingMethod
import com.android.launcher3.Launcher
import com.android.launcher3.R

data object StartpageEU : QsbSearchProvider(
    id = "startpage-eu",
    name = R.string.search_provider_startpage_eu,
    icon = R.drawable.ic_startpage,
    themingMethod = ThemingMethod.TINT,
    packageName = "",
    website = "https://eu.startpage.com/?segment=startpage.qqlauncher",
    type = QsbSearchProviderType.LOCAL,
    sponsored = false,
) {
    override suspend fun launch(launcher: Launcher, forceWebsite: Boolean) {
        val prefs = PreferenceManager.getInstance(launcher)
        val useWebSuggestions = prefs.searchResultStartPageSuggestion.get()

        if (useWebSuggestions) {
            launcher.animateToAllApps()
            launcher.appsView.searchUiManager.editText?.showKeyboard()
        } else {
            super.launch(launcher, forceWebsite)
        }
    }
}
