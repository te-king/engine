package world.controllers

import graphics.Device
import world.Scene


actual class GraphicsContext actual constructor(scene: Scene) : Controller(scene) {

    actual val device: Device
        get() = TODO("Not yet implemented")

}