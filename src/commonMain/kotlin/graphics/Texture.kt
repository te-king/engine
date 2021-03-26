package graphics


expect class Texture<F : TextureFormat, K : TextureDimensions> {

    val device: Device

    inner class Context

}

expect inline operator fun <F : TextureFormat, K : TextureDimensions> Texture<F, K>.invoke(index: Int, context: Texture<F, K>.Context.() -> Unit)


