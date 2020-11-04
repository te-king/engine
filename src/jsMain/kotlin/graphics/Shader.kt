package graphics

import org.khronos.webgl.WebGLShader


actual class Shader<K : ShaderKind>(actual val device: Device, val handle: WebGLShader, val kind: K) {

    actual inner class Context

    actual inline operator fun invoke(context: Context.() -> Unit) {
        TODO()
    }

}