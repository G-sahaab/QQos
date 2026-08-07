package app.qqlauncher.gestures.handlers

import android.content.Context
import app.qqlauncher.QQ LauncherLauncher
import app.qqlauncher.preferences2.PreferenceManager2
import app.qqlauncher.qsb.LawnQsbLayout

class OpenSearchGestureHandler(context: Context) : GestureHandler(context) {

    override suspend fun onTrigger(launcher: QQ LauncherLauncher) {
        val prefs = PreferenceManager2.getInstance(launcher)
        val searchProvider = LawnQsbLayout.getSearchProvider(launcher, prefs)
        searchProvider.launch(launcher)
    }
}
