package extensions

import graphics.RGB8
import graphics.Texture2d
import graphics.TextureFormat
import graphics.TextureDimensions
import org.lwjgl.opengl.GL46C.*


val TextureDimensions.native
    get() = when (this) {
        Texture2d -> GL_TEXTURE_2D
    }

val TextureFormat.native
    get() = when (this) {
        RGB8 -> GL_RGB8
    }