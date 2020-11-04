package graphics


expect class Pipeline {

    val device: Device

    val vertexShader: Shader<VertexShader>

    val fragmentShader: Shader<FragmentShader>


    inner class Context

    inline operator fun invoke(context: Context.() -> Unit)

}

