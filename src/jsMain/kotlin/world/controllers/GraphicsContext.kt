package world.controllers

import graphics.Device
import kotlinx.browser.document
import org.w3c.dom.HTMLCanvasElement
import world.Scene


actual class GraphicsContext actual constructor(scene: Scene) : Controller(scene) {

    private val canvas = document.getElementById("tempest") as HTMLCanvasElement? ?: error("could not find tempest canvas.")

    actual val device = canvas.let(::Device)

}