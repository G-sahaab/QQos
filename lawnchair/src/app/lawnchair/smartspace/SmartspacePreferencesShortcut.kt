package app.qqlauncher.smartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import app.qqlauncher.ui.preferences.PreferenceActivity
import app.qqlauncher.ui.preferences.navigation.SmartspaceWidget

class SmartspacePreferencesShortcut : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(PreferenceActivity.createIntent(this, SmartspaceWidget))
        finish()
    }
}
