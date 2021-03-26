package graphics


actual class Texture<F : TextureFormat, K : TextureDimensions>(actual val device: Device, val id: Int, val format: F, val kind: K) {

    actual inner class Context {
        val device get() = this@Texture.device
    }

}

actual inline operator fun <F : TextureFormat, K : TextureDimensions> Texture<F, K>.invoke(index: Int, context: Texture<F, K>.Context.() -> Unit) =
    Context().let(context)