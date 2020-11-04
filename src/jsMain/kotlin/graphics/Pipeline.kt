package graphics

import org.khronos.webgl.WebGLProgram


actual class Pipeline(actual val device: Device, val handle: WebGLProgram, actual val vertexShader: Shader<VertexShader>, actual val fragmentShader: Shader<FragmentShader>) {

    actual inner class Context

    actual inline operator fun invoke(context: Context.() -> Unit) {
        Context().apply {
            device.context.linkProgram(handle)
            context()
            device.context.linkProgram(null)
        }
    }

}