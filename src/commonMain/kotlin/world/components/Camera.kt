package world.components

import extensions.sizeOf
import graphics.DataBuffer
import graphics.DrawCommandBuffer
import graphics.DynamicBuffer
import graphics.writeData
import math.Float4x4
import math.ProjectionMatrix
import world.Node
import world.controllers.GraphicsContext
import kotlin.properties.Delegates


class Camera(node: Node) : Component(node) {

    private val graphicsContext by controller<GraphicsContext>()
    private val transform by component<Transform>()


    private val buffer = graphicsContext.device.createBuffer(sizeOf(Float4x4::class), DataBuffer, DynamicBuffer) ?: error("Failed to create Camera buffer")


    var fieldOfView by Delegates.observable(0.7854f) { _, _, _ -> projectionInvalid = true }
    var aspectRatio by Delegates.observable(640f / 480f) { _, _, _ -> projectionInvalid = true }
    var nearPlaneClipping by Delegates.observable(0.05f) { _, _, _ -> projectionInvalid = true }
    var farPlaneClipping by Delegates.observable(500f) { _, _, _ -> projectionInvalid = true }


    // Projection Matrix
    private var projectionInvalid = true

    var projectionMatrix = ProjectionMatrix.identity
        get() {
            if (projectionInvalid) {
                projectionMatrix = ProjectionMatrix.perspectiveFieldOfView(fieldOfView, aspectRatio, nearPlaneClipping, farPlaneClipping)
                projectionInvalid = false
            }
            return field
        }
        private set(value) {
            buffer { writeData(0, value.matrix) }
            field = value
        }


    fun attach(commandBuffer: DrawCommandBuffer) {
        transform.worldMatrix
        commandBuffer.bindDataBuffer(0, transform.buffer)
        projectionMatrix
        commandBuffer.bindDataBuffer(1, buffer)
    }

}