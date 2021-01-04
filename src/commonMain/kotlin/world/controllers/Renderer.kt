package world.controllers

import graphics.*
import math.Color
import world.Scene
import world.Updatable
import world.components.Camera


expect class Renderer(scene: Scene) : Controller {

    var currentCamera: Camera?

    interface Renderable {
        fun draw(commandBuffer: DrawCommandBuffer)
    }

}