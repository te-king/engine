import world.Client
import world.components.Camera
import world.components.Drawable
import world.components.Transform
import world.controllers.*
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
    val renderer = currentScene.getOrAdd(Renderer::class, ::Renderer)

    val cameraNode = currentScene.spawn()
    val camera = cameraNode.getOrAdd(Camera::class, ::Camera)

    val triangleNode = currentScene.spawn()
    val drawable = triangleNode.getOrAdd(Drawable::class, ::Drawable)

    client.start()

}