package app.qqos.aether.widgets

object WidgetManager {

    val widgets = mutableListOf<String>()

    fun register(name: String) {
        widgets.add(name)
    }
}
