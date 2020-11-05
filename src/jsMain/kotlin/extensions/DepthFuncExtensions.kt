package extensions

import graphics.DepthFunc
import org.khronos.webgl.WebGL2RenderingContext.Companion.ALWAYS
import org.khronos.webgl.WebGL2RenderingContext.Companion.EQUAL
import org.khronos.webgl.WebGL2RenderingContext.Companion.GEQUAL
import org.khronos.webgl.WebGL2RenderingContext.Companion.GREATER
import org.khronos.webgl.WebGL2RenderingContext.Companion.LEQUAL
import org.khronos.webgl.WebGL2RenderingContext.Companion.LESS
import org.khronos.webgl.WebGL2RenderingContext.Companion.NEVER
import org.khronos.webgl.WebGL2RenderingContext.Companion.NOTEQUAL


val DepthFunc.native
    get() = when (this) {
        DepthFunc.Never -> NEVER
        DepthFunc.Less -> LESS
        DepthFunc.Greater -> GREATER
        DepthFunc.Equal -> EQUAL
        DepthFunc.Always -> ALWAYS
        DepthFunc.LEqual -> LEQUAL
        DepthFunc.GEqual -> GEQUAL
        DepthFunc.NotEqual -> NOTEQUAL
    }