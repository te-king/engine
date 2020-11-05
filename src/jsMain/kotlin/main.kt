import extensions.sizeOf
import graphics.*
import math.*
import world.Client
import world.components.Camera
import world.components.CameraControls
import world.components.Drawable
import world.components.Transform
import world.controllers.Renderer
import world.controllers.GraphicsContext
import world.controllers.Input
import world.controllers.StandardShader

fun main() {

    val client = Client()
    val currentScene = client.currentScene


    val graphicsContext = currentScene.add(GraphicsContext::class)
    val input = currentScene.add(Input::class)
    val renderer = currentScene.add(Renderer::class)
    val standardShader = currentScene.add(StandardShader::class)


    val node = currentScene.addNode {

        val transform = add(Transform::class)
        val drawable = add(Drawable::class)


        val trianglePositions = Float4Array(3)
        trianglePositions[0] = Float4(0f, 0f, 0f, 1f)
        trianglePositions[1] = Float4(1f, 1f, 0f, 1f)
        trianglePositions[2] = Float4(1f, 0f, 0f, 1f)

        val triangleIndices = intArrayOf(2, 1, 0)

        val triangleVertexBuffer = graphicsContext.device.createBuffer(sizeOf(Float4::class, Float4::class, Float4::class), VertexBuffer, DynamicBuffer) ?: error(Unit)
        triangleVertexBuffer { writeData(0, trianglePositions) }
        val triangleVertexBufferObject = VertexBufferObject(triangleVertexBuffer, Float4::class, 0)

        val triangleIndicesBuffer = graphicsContext.device.createBuffer(sizeOf(Int3::class), IndexBuffer, DynamicBuffer) ?: error(Unit)
        triangleIndicesBuffer { writeData(0, triangleIndices) }
        val triangleIndicesBufferObject = IndexBufferObject(triangleIndicesBuffer, 3, PrimitiveType.Triangles)

        val mesh = MeshBufferObject(mapOf(0 to triangleVertexBufferObject), listOf(triangleIndicesBufferObject))
        val material = standardShader.Material()
        material.diffuseColor = Color.red

        drawable.pairs.add(mesh to material)
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