package graphics


class Image<F: TextureFormat, K: TextureDimensions>(val texture: Texture<F, K>, val level: Int)