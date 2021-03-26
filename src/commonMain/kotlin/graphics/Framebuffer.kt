package graphics


expect class Framebuffer {

    val device: Device

    inner class Context

}

expect inline operator fun Framebuffer.invoke(context: Framebuffer.Context.() -> Unit)

