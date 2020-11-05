package world.controllers

import world.Scene


expect class Input(scene: Scene) : Controller {

    val keyboardState: MutableMap<String, Boolean>

    interface KeyboardInputReceiver {
        fun keyboardKeyPressed(key: String)
        fun keyboardKeyReleased(key: String)
    }

    interface MouseInputReceiver {
        fun mouseButtonPressed(button: String)
        fun mouseButtonReleased(button: String)
    }

}