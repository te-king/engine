package extensions

import graphics.Image
import graphics.Texture
import graphics.TextureFormat
import graphics.TextureKind


operator fun <F : TextureFormat, K : TextureKind> Texture<F, K>.get(level: Int) = Image(this, level)