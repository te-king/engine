package world.controllers

import graphics.*
import math.Color
import world.Scene
import world.Updatable
import world.components.Camera


class Renderer(scene: Scene) : Controller(scene), Updatable {

    private val graphicsContext by controller(::GraphicsContext)
    private val renderables by components<Renderable>()


    private val state = DeviceState(
        writeFramebuffer = null,
        winding = FaceWinding.CounterClockWise,
        cullFunc = CullMode.Back,
    )


    var currentCamera: Camera? = null


    override fun update(delta: Double) {
        graphicsContext.device.draw(state) {
            clearFramebuffer(Color.black)
            currentCamera?.attach(this) ?: return@draw
            for (renderable in renderables) renderable.draw(this)
        }
    }


    interface Renderable {
        fun draw(commandBuffer: DrawCommandBuffer)
    }

}