package app.qqlauncher.gestures.handlers

import android.content.Context
import app.qqlauncher.QQ LauncherLauncher
import app.qqlauncher.animateToAllApps

class OpenAppDrawerGestureHandler(context: Context) : GestureHandler(context) {

    override suspend fun onTrigger(launcher: QQ LauncherLauncher) {
        launcher.animateToAllApps()
    }
}
