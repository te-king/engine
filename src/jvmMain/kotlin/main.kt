import world.Client
import world.Scene
import world.controllers.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {

    val client = Client()
    val currentScene = client.currentScene

    /***
     * Controllers
     */
    val graphicsContext = currentScene.getOrAdd(::GraphicsContext)
    val input = currentScene.getOrAdd(::Input)
    val physics = currentScene.getOrAdd(::Physics)


    client.start()

}