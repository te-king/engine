package graphics

actual class Pipeline(actual val device: Device, val id: Int, actual val vertexShader: Shader<VertexShader>, actual val fragmentShader: Shader<FragmentShader>) {

    actual inner class Context

}

actual inline operator fun Pipeline.invoke(context: Pipeline.Context.() -> Unit) =
    Context().let(context)