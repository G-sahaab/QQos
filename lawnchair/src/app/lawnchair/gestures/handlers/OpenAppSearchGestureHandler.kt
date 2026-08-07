package app.qqlauncher.gestures.handlers

import android.content.Context
import app.qqlauncher.QQ LauncherLauncher
import app.qqlauncher.animateToAllApps

class OpenAppSearchGestureHandler(context: Context) : GestureHandler(context) {

    override suspend fun onTrigger(launcher: QQ LauncherLauncher) {
        val searchUiManager = launcher.appsView.searchUiManager
        searchUiManager.setDirectFocus(true)
        searchUiManager.editText?.showKeyboard()
        launcher.animateToAllApps()
    }
}
