package world.controllers

import org.lwjgl.glfw.GLFW.*
import world.Scene

actual class Input actual constructor(scene: Scene) : Controller(scene) {

    private val graphicsContext by controller(::GraphicsContext)

    private val keyboardReceivers by components<KeyboardInputReceiver>()
    private val mouseReceivers by components<MouseInputReceiver>()


    actual val keyboardState = mutableMapOf<String, Boolean>()


    init {
        glfwSetKeyCallback(graphicsContext.window) { l: Long, i: Int, i1: Int, i2: Int, i3: Int ->
            println("$l $i $i1 $i2 $i3")
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