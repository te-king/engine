package world.controllers

import graphics.Device
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11C.GL_VERSION
import org.lwjgl.opengl.GL11C.glGetString
import org.lwjgl.opengl.GLUtil
import world.Scene
import world.Updatable
import java.util.concurrent.Executors


actual class GraphicsContext actual constructor(scene: Scene) : Controller(scene), Updatable {

    init {
        if (!glfwInit()) throw error("Failed to initialize glfw.")
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
    }

    val window = glfwCreateWindow(640, 480, "Tempest Main Window", 0, 0)

    val context = newSingleThreadContext("GL.Thread")

    init {
        runBlocking(context) {
            glfwMakeContextCurrent(window)
            GL.createCapabilities()
            GLUtil.setupDebugMessageCallback()
        }
    }

    actual val device = Device(context)


    override fun update(delta: Double) {
        glfwPollEvents()
    }

}