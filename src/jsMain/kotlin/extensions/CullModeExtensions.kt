package extensions

import graphics.CullMode
import org.khronos.webgl.WebGL2RenderingContext.Companion.BACK
import org.khronos.webgl.WebGL2RenderingContext.Companion.FRONT
import org.khronos.webgl.WebGL2RenderingContext.Companion.FRONT_AND_BACK


val CullMode.native
    get() = when (this) {
        CullMode.Front -> FRONT
        CullMode.Back -> BACK
        CullMode.FrontAndBack -> FRONT_AND_BACK
    }