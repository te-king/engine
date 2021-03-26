package world.controllers

import org.lwjgl.glfw.GLFW.*
import world.Scene


actual class Input actual constructor(scene: Scene) : Controller(scene) {

    private val graphicsContext by controller(::GraphicsContext)


    private val keyboardReceivers by components<KeyboardInputReceiver>()
    private val mouseReceivers by components<MouseInputReceiver>()


    actual val keyboardState: MutableMap<String, Boolean>
        get() = TODO("Not yet implemented")


    init {
        glfwSetKeyCallback(graphicsContext.window) { window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
            if (action == 1)
                for (receiver in keyboardReceivers)
                    receiver.keyboardKeyPressed(key.toString())
            else
                for (receiver in keyboardReceivers)
                    receiver.keyboardKeyReleased(key.toString())
        }
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