package world

import extensions.controllersOfType
import kotlinx.browser.window

actual class Client {

    actual var currentScene = Scene()


    private var shouldClose = false

    actual fun exit() {
        shouldClose = true
    }

    actual fun start() {

        fun loop(delta: Double) {
            for (updatable in currentScene.controllersOfType<Updatable>()) updatable.update(delta / 1000.0)
            if (!shouldClose) window.requestAnimationFrame(::loop)
        }

        loop(0.0)

    }

}