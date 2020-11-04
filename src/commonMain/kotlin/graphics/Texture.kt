package graphics


expect class Texture<F : TextureFormat, K : TextureKind> {

    val device: Device


    inner class Context

    inline operator fun invoke(index: Int, context: Context.() -> Unit)

}


