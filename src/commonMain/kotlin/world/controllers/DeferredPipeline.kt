package world.controllers

import extensions.draw
import graphics.DrawCommandBuffer
import math.Color
import world.Scene
import world.Updatable
import world.components.Camera


class DeferredPipeline(scene: Scene) : Controller(scene), Updatable {

    private val graphicsContext by controller<GraphicsContext>()
    private val renderables by components<Renderable>()


    var currentCamera: Camera? = null


    override fun update(delta: Double) {

        graphicsContext.device.draw {

            clearFramebuffer(Color.black)

            currentCamera?.attach(this) ?: return@draw
            for (renderable in renderables) renderable.draw(this)

        }

    }


    interface Renderable {
        fun draw(commandBuffer: DrawCommandBuffer)
    }

}