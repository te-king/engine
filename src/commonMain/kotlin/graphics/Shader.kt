package graphics


expect class Shader<K : ShaderKind> {

    val device: Device

    inner class Context

}

expect inline operator fun <K : ShaderKind> Shader<K>.invoke(context: Shader<K>.Context.() -> Unit)