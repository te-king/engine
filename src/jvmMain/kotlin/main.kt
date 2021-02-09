import extensions.pairedPermutations
import extensions.writeData
import graphics.DataKind
import graphics.DynamicStorage
import graphics.invoke
import graphics.writeData
import math.int3ArrayOf
import world.Client
import world.Scene
import world.controllers.*
import kotlin.system.measureNanoTime
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {

    val client = Client()
    val currentScene = client.currentScene


//    /***
//     * Controllers
//     */
    val graphics = currentScene.getOrAdd(GraphicsContext::class, ::GraphicsContext)
//    val input = currentScene.getOrAdd(::Input)
//    val physics = currentScene.getOrAdd(::PhysicsContext)
//    val renderer = currentScene.getOrAdd(::Renderer)

    val buf = graphics.device.createBuffer(100, DataKind, DynamicStorage) ?: return

    buf {
        writeData(0, intArrayOf(1, 2, 3))
    }


//    client.start()

}