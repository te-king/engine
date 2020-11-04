package graphics

actual class Texture<F : TextureFormat, K : TextureKind>(actual val device: Device) {

    actual inner class Context

    actual inline operator fun invoke(index: Int, context: Context.() -> Unit) {

    }

}