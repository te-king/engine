import extensions.createMeshBufferObject
import graphics.TriangleMesh
import math.Color
import math.Float3
import physics.Container
import world.Client
import world.Node
import world.Scene
import world.components.*
import world.controllers.*


fun main() {

    val client = Client()
    val currentScene = client.currentScene

    /***
     * Controllers
     */
    val graphicsContext = currentScene.getOrAdd(GraphicsContext::class, ::GraphicsContext)
    val input = currentScene.getOrAdd(Input::class, ::Input)
    val physics = currentScene.getOrAdd(PhysicsContext::class, ::PhysicsContext)
    val renderer = currentScene.getOrAdd(Renderer::class, ::Renderer)
    val standardShader = currentScene.getOrAdd(StandardShader::class, ::StandardShader)

    /***
     * Nodes
     */
    val boxNode = currentScene.Node {

        val body = getOrAdd(PhysicsBody::class, ::PhysicsBody)
        body.static = true
        body.collider = Container

    }


    val cameraNode = currentScene.Node {

        val transform = getOrAdd(Transform::class, ::Transform)
        val camera = getOrAdd(Camera::class, ::Camera)
        getOrAdd(CameraControls::class, ::CameraControls)
        transform.translation = Float3(0f, 0f, 5f)
        renderer.currentCamera = camera

    }


    val triangleNode = currentScene.Node {

        val transform = getOrAdd(Transform::class, ::Transform)
        val drawable = getOrAdd(Drawable::class, ::Drawable)
        getOrAdd(PhysicsBody::class, ::PhysicsBody)

        transform.translation = Float3(0f, 20f, 0f)
        val mesh = graphicsContext.device.createMeshBufferObject(TriangleMesh) ?: error("Failed to create mesh buffer object")
        val material = standardShader.Material().also { it.diffuseColor = Color.green }
        drawable.pairs.add(mesh to material)

    }


    client.start()

}