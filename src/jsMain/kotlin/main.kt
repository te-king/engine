import extensions.createBuffer
import graphics.*
import math.*
import physics.Container
import world.Client
import world.components.*
import world.controllers.*
import kotlin.js.Date
import kotlin.random.Random
import kotlin.time.TimeMark

fun main() {

    val client = Client()
    val currentScene = client.currentScene


    val graphicsContext = currentScene.add(GraphicsContext::class)
    val input = currentScene.add(Input::class)
    val physics = currentScene.add(Physics::class)
    val renderer = currentScene.add(Renderer::class)
    val standardShader = currentScene.add(StandardShader::class)


    val rand = Random(Date.now().toInt())

    repeat(8) {

        currentScene.addNode {

            val transform = add(Transform::class)
            val drawable = add(Drawable::class)
            val body = add(Body::class)

            transform.translation = Float3(rand.nextDouble(-1.0, 1.0).toFloat(), (it + 5).toFloat(), rand.nextDouble(-1.0, 1.0).toFloat())

            val trianglePositions = float4ArrayOf(
                Float4(0f, 0f, 0f, 1f),
                Float4(1f, 1f, 0f, 1f),
                Float4(1f, 0f, 0f, 1f)
            )

            val triangleIndices = intArrayOf(
                2,
                1,
                0
            )

            val triangleVertexBuffer = graphicsContext.device.createBuffer(trianglePositions, VertexBuffer, ServerBuffer) ?: error(Unit)
            val triangleVertexBufferObject = VertexBufferObject(triangleVertexBuffer, Float4::class, 0)

            val triangleIndicesBuffer = graphicsContext.device.createBuffer(triangleIndices, IndexBuffer, ServerBuffer) ?: error(Unit)
            val triangleIndicesBufferObject = IndexBufferObject(triangleIndicesBuffer, 3, PrimitiveType.Triangles)

            val mesh = MeshBufferObject(mapOf(0 to triangleVertexBufferObject), listOf(triangleIndicesBufferObject))

            val material = standardShader.Material()
            material.diffuseColor = Color.green

            drawable.pairs.add(mesh to material)
        }

    }

    val boxNode = currentScene.addNode {
        val body = add(Body::class)
        body.static = true
        body.collider = Container
    }


    val cameraNode = currentScene.addNode {
        val transform = add(Transform::class)
        val camera = add(Camera::class)
        val cameraControls = add(CameraControls::class)

        transform.translation = Float3(0f, 0f, 5f)

        renderer.currentCamera = camera
    }

    client.start()

}