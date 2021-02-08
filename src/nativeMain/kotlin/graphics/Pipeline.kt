package graphics


actual class Pipeline(actual val device: Device, actual val vertexShader: Shader<VertexShader>, actual val fragmentShader: Shader<FragmentShader>) {

    actual inner class Context

    actual inline operator fun invoke(context: Context.() -> Unit) {
        TODO("Not yet implemented")
    }

}