package graphics

import extensions.native


actual class Texture<F : TextureFormat, K : TextureKind>(actual val device: Device, val id: Int, val format: F, val kind: K) {

    actual inner class Context {
        val device get() = this@Texture.device
        val id get() = this@Texture.id
        val kind = this@Texture.kind.native
        val format = this@Texture.format.native
    }

    actual inline operator fun invoke(index: Int, context: Context.() -> Unit) = Context().context()

}