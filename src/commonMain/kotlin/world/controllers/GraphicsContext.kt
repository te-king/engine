package world.controllers

import graphics.Device
import world.Scene


expect class GraphicsContext(scene: Scene) : Controller {

    val device: Device

}