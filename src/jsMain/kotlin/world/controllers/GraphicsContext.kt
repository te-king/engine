package world.controllers

import graphics.Device
import kotlinx.browser.document
import org.khronos.webgl.WebGL2RenderingContext
import org.w3c.dom.HTMLCanvasElement
import world.Scene


actual class GraphicsContext actual constructor(scene: Scene) : Controller(scene) {

    private val canvas = document.getElementById("tempest") as HTMLCanvasElement? ?: error("could not find tempest canvas.")

    private val context = canvas.getContext("webgl2") as WebGL2RenderingContext? ?: error("could not get webgl2 context.")

    actual val device = Device(context)

}