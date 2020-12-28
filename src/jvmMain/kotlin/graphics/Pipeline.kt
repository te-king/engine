package graphics

actual class Pipeline(actual val device: Device, val id: Int, actual val vertexShader: Shader<VertexShader>, actual val fragmentShader: Shader<FragmentShader>) {

    actual inner class Context {
        val id get() = this@Pipeline.id
    }

    actual inline operator fun invoke(context: Context.() -> Unit) = Context().context()

}