package graphics


expect class Shader<T: ShaderKind> {

    val device: Device


    inner class Context

    inline operator fun invoke(context: Context.() -> Unit)

}