package world.controllers

import world.Scene

actual class Input actual constructor(scene: Scene): Controller(scene) {

    actual val keyboardState = mutableMapOf<String, Boolean>()

    actual interface KeyboardInputReceiver {
        actual fun keyboardKeyPressed(key: String)
        actual fun keyboardKeyReleased(key: String)
    }

    actual interface MouseInputReceiver {
        actual fun mouseButtonPressed(button: String)
        actual fun mouseButtonReleased(button: String)
    }

}