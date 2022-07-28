import ecs.*
import kotlinx.coroutines.*
import math.Float3
import world.Client
import world.components.Label
import world.components.PhysicsBody
import world.components.Transform
import kotlin.concurrent.fixedRateTimer


enum class State {
    Enabled,
    Disabled
}


fun main() {

    val client = Client()


    client.currentBackend.spawn(Label("Entity 1"), Transform(), PhysicsBody(positionDelta = Float3.up))


    val updateTransformSystem = system(Exists<Transform>(), Exists<PhysicsBody>()) { transform, physicsBody ->
        addOrUpdate(
            Transform(
                transform.position + physicsBody.positionDelta,
                transform.rotation * physicsBody.rotationDelta,
                transform.scale
            )
        )
    }

    val deltaDebugSystem = system(Exists<Double>()) {
        println(it)
    }

    val debugTransformSystem = system(Exists<Transform>()) {
        println(it)
    }

    client.start(listOf(updateTransformSystem, debugTransformSystem, deltaDebugSystem))

}