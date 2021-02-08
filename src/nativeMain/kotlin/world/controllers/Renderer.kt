package world.controllers

import graphics.*
import math.Color
import world.Scene
import world.Updatable
import world.components.Camera


actual class Renderer actual constructor(scene: Scene) : Controller(scene), Updatable {

    private val graphicsContext by controller(::GraphicsContext)
    private val renderables by components<Renderable>()


    private val state = DeviceState(
        writeFramebuffer = null,
        winding = FaceWinding.CounterClockWise,
        cullFunc = CullMode.Back,
    )


    actual var currentCamera: Camera? = null


    override fun update(delta: Double) {

        val commandBuffer = graphicsContext.device.createDrawCommandBuffer(state) ?: error("Failed to create draw command buffer")

        commandBuffer.clearFramebuffer(Color.black)
        currentCamera?.attach(commandBuffer)
        for (renderable in renderables) renderable.draw(commandBuffer)

        commandBuffer.submit()

    }


    actual interface Renderable {
        actual fun draw(commandBuffer: DrawCommandBuffer)
    }

}