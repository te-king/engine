import extensions.sizeOf
import graphics.*
import math.Float3
import math.Float4
import math.Float4Array
import math.Int3
import world.Client
import world.components.Camera
import world.components.Drawable
import world.components.Transform
import world.controllers.DeferredPipeline
import world.controllers.GraphicsContext
import world.controllers.StandardShader

fun main() {

    val client = Client()
    val currentScene = client.currentScene


    val graphicsContext = currentScene.add(GraphicsContext::class)
    val deferredPipeline = currentScene.add(DeferredPipeline::class)
    val standardShader = currentScene.add(StandardShader::class)


    val node = currentScene.addNode {


        val trianglePositions = Float4Array(3)
        trianglePositions[0] = Float4(0f, 0f, 0f, 1f)
        trianglePositions[1] = Float4(1f, 1f, 0f, 1f)
        trianglePositions[2] = Float4(1f, 0f, 0f, 1f)

        val triangleIndices = intArrayOf(0, 1, 2)


        val triangleVertexBuffer = graphicsContext.device.createBuffer(sizeOf(Float4::class, Float4::class, Float4::class), VertexBuffer, DynamicBuffer) ?: error(Unit)
        triangleVertexBuffer { writeData(0, trianglePositions) }
        val triangleVertexBufferObject = VertexBufferObject(triangleVertexBuffer, Float4::class, 0)

        val triangleIndicesBuffer = graphicsContext.device.createBuffer(sizeOf(Int3::class), IndexBuffer, DynamicBuffer) ?: error(Unit)
        triangleIndicesBuffer { writeData(0, triangleIndices) }
        val triangleIndicesBufferObject = IndexBufferObject(triangleIndicesBuffer, 3, PrimitiveType.Triangles)


        val mesh = MeshBufferObject(
            mapOf(0 to triangleVertexBufferObject),
            listOf(triangleIndicesBufferObject)
        )

        val material = standardShader.Material()

        val transform = add(Transform::class)
        transform.scale = Float3(0.5f, 0.5f, 0.5f)

        val drawable = add(Drawable::class)
        drawable.pairs.add(mesh to material)
    }


    val cameraNode = currentScene.addNode {
        val camera = add(Camera::class)
        deferredPipeline.currentCamera = camera
    }

    client.start()

}