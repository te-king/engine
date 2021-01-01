import extensions.createMeshBufferObject
import graphics.TriangleMesh
import math.Color
import math.Float3
import physics.Container
import world.Client
import world.components.*
import world.controllers.*
import kotlin.js.Date
import kotlin.random.Random

fun main() {

    val client = Client()
    val currentScene = client.currentScene

    /***
     * Controllers
     */
    val graphicsContext = currentScene.getOrAdd(::GraphicsContext)
    val input = currentScene.getOrAdd(::Input)
    val physics = currentScene.getOrAdd(::Physics)
    val renderer = currentScene.getOrAdd(::Renderer)
    val standardShader = currentScene.getOrAdd(::StandardShader)

    /***
     * Nodes
     */
    val boxNode = currentScene.node()

    boxNode.apply {
        val body = getOrAdd(::Body)
        body.static = true
        body.collider = Container
    }


    val cameraNode = currentScene.node()

    cameraNode.apply {
        val transform = getOrAdd(::Transform)
        val camera = getOrAdd(::Camera)
        getOrAdd(::CameraControls)
        transform.translation = Float3(0f, 0f, 5f)
        renderer.currentCamera = camera
    }


//    repeat(4) {
//        val triangleNode = currentScene.node()
//        triangleNode.apply {
//            val transform = getOrAdd(::Transform)
//            val drawable = getOrAdd(::Drawable)
//            getOrAdd(::Body)
//
//            transform.translation = Float3(0f, 5f + it, 0f)
//            val mesh = graphicsContext.device.createMeshBufferObject(TriangleMesh) ?: error("Failed to create mesh buffer object")
//            val material = standardShader.Material().also { it.diffuseColor = Color.green }
//            drawable.pairs.add(mesh to material)
//        }
//    }


    val triangleNode = currentScene.node()

    triangleNode.apply {
        val transform = getOrAdd(::Transform)
        val drawable = getOrAdd(::Drawable)
        getOrAdd(::Body)

        transform.translation = Float3(0f, 20f, 0f)
        val mesh = graphicsContext.device.createMeshBufferObject(TriangleMesh) ?: error("Failed to create mesh buffer object")
        val material = standardShader.Material().also { it.diffuseColor = Color.green }
        drawable.pairs.add(mesh to material)
    }


    client.start()

}