package world

import extensions.componentsOfType
import extensions.controllersOfType
import kotlinx.browser.window

actual class Client {

    actual var currentScene = Scene()


    private var shouldClose = false

    actual fun exit() {
        shouldClose = true
    }

    actual fun start() {

        var lastTime = 0.0

        fun loop(time: Double) {
            for (updatable in currentScene.controllersOfType<Updatable>()) updatable.update((time - lastTime) / 1000.0)
            for (updatable in currentScene.componentsOfType<Updatable>()) updatable.update((time - lastTime) / 1000.0)
            lastTime = time
            if (!shouldClose) window.requestAnimationFrame(::loop)
        }

        loop(0.0)

    }

}