package world

import extensions.componentsOfType
import extensions.controllersOfType
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

actual class Client {

    var shouldClose = false

    actual var currentScene = Scene()

    actual fun exit() {
        shouldClose = true
    }

    @ExperimentalTime
    actual fun start() {

        var mark = TimeSource.Monotonic.markNow()

        while (!shouldClose) {
            for (updatable in currentScene.controllersOfType<Updatable>()) updatable.update(mark.elapsedNow().inSeconds)
            for (updatable in currentScene.componentsOfType<Updatable>()) updatable.update(mark.elapsedNow().inSeconds)
            mark = TimeSource.Monotonic.markNow()
        }

    }

}