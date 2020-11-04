package extensions

import graphics.PrimitiveType
import org.khronos.webgl.WebGL2RenderingContext


val PrimitiveType.native
    get() = when (this) {
        PrimitiveType.Triangles -> WebGL2RenderingContext.TRIANGLES
    }