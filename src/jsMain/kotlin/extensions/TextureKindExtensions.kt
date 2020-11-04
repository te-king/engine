package extensions

import graphics.RGB8
import graphics.Texture2d
import graphics.TextureFormat
import graphics.TextureKind
import org.khronos.webgl.WebGL2RenderingContext
import org.khronos.webgl.WebGLRenderingContext


val TextureKind.native
    get() = when (this) {
        Texture2d -> WebGLRenderingContext.TEXTURE_2D
    }

val TextureFormat.native
    get() = when (this) {
        RGB8 -> WebGL2RenderingContext.RGB8
    }