package world.controllers

import kotlinx.browser.window
import org.w3c.dom.events.KeyboardEvent
import world.Scene

actual class Input actual constructor(scene: Scene) : Controller(scene) {

    private val keyboardReceivers by components<KeyboardInputReceiver>()
    private val mouseReceivers by components<MouseInputReceiver>()


    actual val keyboardState = mutableMapOf<String, Boolean>().withDefault { false }


    init {
        window.addEventListener("keydown", {
            it as KeyboardEvent
            keyboardState[it.key] = true
            for (receiver in keyboardReceivers) receiver.keyboardKeyPressed(it.key)
        })

        window.addEventListener("keyup", {
            it as KeyboardEvent
            keyboardState[it.key] = false
            for (receiver in keyboardReceivers) receiver.keyboardKeyReleased(it.key)
        })
    }


    actual interface KeyboardInputReceiver {
        actual fun keyboardKeyPressed(key: String)
        actual fun keyboardKeyReleased(key: String)
    }

    actual interface MouseInputReceiver {
        actual fun mouseButtonPressed(button: String)
        actual fun mouseButtonReleased(button: String)
    }

}