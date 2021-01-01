package world.controllers

import graphics.Device
import platform.Metal.MTLCreateSystemDefaultDevice
import world.Scene
import world.Updatable

actual class GraphicsContext actual constructor(scene: Scene) : Controller(scene) {

    private val mtlDevice = MTLCreateSystemDefaultDevice() ?: error("Could not create default device")

    actual val device = Device(mtlDevice)

}