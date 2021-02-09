package extensions

import graphics.BufferStorage
import graphics.ClientStorage
import graphics.DynamicStorage
import graphics.ServerStorage
import org.khronos.webgl.WebGL2RenderingContext.Companion.DYNAMIC_COPY
import org.khronos.webgl.WebGL2RenderingContext.Companion.DYNAMIC_DRAW
import org.khronos.webgl.WebGL2RenderingContext.Companion.STATIC_DRAW


val BufferStorage.native
    get() = when (this) {
        DynamicStorage -> DYNAMIC_DRAW
        ClientStorage -> DYNAMIC_COPY
        ServerStorage -> STATIC_DRAW
    }
