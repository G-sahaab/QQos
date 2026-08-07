/*
 * Copyright 2026, QQ Launcher
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

package app.qqlauncher.gestures.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import app.qqlauncher.QQ LauncherLauncher

class RunHandlerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.action == QQ LauncherShortcutActivity.START_ACTION) {
            startActivity(
                Intent(this, QQ LauncherLauncher::class.java).apply {
                    action = QQ LauncherShortcutActivity.START_ACTION
                    putExtra(
                        QQ LauncherShortcutActivity.EXTRA_HANDLER,
                        intent.getStringExtra(QQ LauncherShortcutActivity.EXTRA_HANDLER),
                    )
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                },
            )
        }
        finish()
    }
}
