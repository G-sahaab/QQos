package com.qqos.launcher

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setBackgroundColor(Color.parseColor("#0B1220"))
        layout.setPadding(60,120,60,60)

        val title = TextView(this)
        title.text = "QQOS"
        title.textSize = 34f
        title.setTextColor(Color.WHITE)

        val subtitle = TextView(this)
        subtitle.text = "Aether UI Developer Preview"
        subtitle.textSize = 18f
        subtitle.setTextColor(Color.LTGRAY)

        layout.addView(title)
        layout.add

layout.addView(subtitle)

setContentView(layout)
}
}
