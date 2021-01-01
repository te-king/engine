import graphics.BufferKind
import graphics.DataBuffer
import graphics.DynamicBuffer
import graphics.writeData
import platform.posix.exit
import world.Client
import world.controllers.GraphicsContext
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {

    println("Hello World!")

    val client = Client()
    val currentScene = client.currentScene

    /***
     * Controllers
     */
    val graphicsContext = currentScene.getOrAdd(::GraphicsContext)

    val b = graphicsContext.device.createBuffer(0, DataBuffer, DynamicBuffer) ?: error("")

    client.start()

}