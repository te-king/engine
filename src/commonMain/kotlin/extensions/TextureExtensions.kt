package extensions

import graphics.Image
import graphics.Texture
import graphics.TextureFormat
import graphics.TextureDimensions


operator fun <F : TextureFormat, K : TextureDimensions> Texture<F, K>.get(level: Int) = Image(this, level)