import world.Scene
import world.components.Transform

import cglfw.*
import com.kgl.glfw.Window

fun main() {

    val window = glfwCreateWindow(640, 480, "Main Window", null, null)

    while (glfwWindowShouldClose(window) == GLFW_FALSE) {
        glfwPollEvents()
    }

}