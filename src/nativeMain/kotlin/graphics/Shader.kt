package graphics


actual class Shader<K : ShaderKind>(actual val device: Device, val kind: K) {

    actual inner class Context

    actual inline operator fun invoke(context: Context.() -> Unit) {
        TODO()
    }

}