package graphics

import extensions.native
import org.khronos.webgl.WebGLTexture


actual class Texture<F : TextureFormat, K : TextureDimensions>(actual val device: Device, val handle: WebGLTexture, val format: F, val kind: K) {

    actual inner class Context {
        val device get() = this@Texture.device
        val handle get() = this@Texture.handle
        val kind = this@Texture.kind.native
        val format = this@Texture.format.native
    }

    actual inline operator fun invoke(index: Int, context: Context.() -> Unit) {
        Context().apply {
            device.context.activeTexture(index)
            device.context.bindTexture(kind, handle)
            context()
            device.context.bindTexture(kind, null)
        }
    }

}