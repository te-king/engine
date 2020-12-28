package graphics


expect class Shader<K: ShaderKind> {

    val device: Device


    inner class Context

    inline operator fun invoke(context: Context.() -> Unit)

}