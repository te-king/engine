package world

import ecs.ArchetypeBackend
import ecs.System
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.fixedRateTimer


actual class Client {

    private var shouldClose = false

    actual var currentBackend = ArchetypeBackend()

    actual fun exit() {
        shouldClose = true
    }

    actual fun start(systems: List<System>) {
        fixedRateTimer(period = 1000) {
            if (shouldClose) return@fixedRateTimer
            currentBackend.resources[Double::class] = scheduledExecutionTime().toDouble() * 0.001
            runBlocking {
                currentBackend.run(systems)
            }
            this.scheduledExecutionTime()
        }
    }

}