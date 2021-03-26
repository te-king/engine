package graphics


actual class Framebuffer(actual val device: Device, val id: Int) {

    actual inner class Context

}

actual inline operator fun Framebuffer.invoke(context: Framebuffer.Context.() -> Unit) =
    Context().let(context)