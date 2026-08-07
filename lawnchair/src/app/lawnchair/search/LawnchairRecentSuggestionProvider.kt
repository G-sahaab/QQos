package app.qqlauncher.search

import android.content.SearchRecentSuggestionsProvider
import com.android.launcher3.BuildConfig

class QQ LauncherRecentSuggestionProvider : SearchRecentSuggestionsProvider() {
    companion object {
        const val AUTHORITY = BuildConfig.APPLICATION_ID + ".search.QQ LauncherRecentSuggestionProvider"
        const val MODE = DATABASE_MODE_QUERIES
    }

    init {
        setupSuggestions(AUTHORITY, MODE)
    }
}
