package graphics


expect class Pipeline {

    val device: Device

    val vertexShader: Shader<VertexShader>

    val fragmentShader: Shader<FragmentShader>

    inner class Context

}

expect inline operator fun Pipeline.invoke(context: Pipeline.Context.() -> Unit)

