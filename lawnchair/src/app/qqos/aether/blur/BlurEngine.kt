package app.qqos.aether.blur

object BlurEngine {

    const val DISABLED = 0
    const val LIGHT = 20
    const val MEDIUM = 40
    const val HEAVY = 60

    var currentBlur = MEDIUM

    fun setBlur(level: Int) {
        currentBlur = level
    }

    fun getBlur(): Int {
        return currentBlur
    }
}
