package extensions

import graphics.BufferStorage
import graphics.ClientBuffer
import graphics.DynamicBuffer
import graphics.ServerBuffer
import org.khronos.webgl.WebGL2RenderingContext.Companion.DYNAMIC_COPY
import org.khronos.webgl.WebGL2RenderingContext.Companion.DYNAMIC_DRAW
import org.khronos.webgl.WebGL2RenderingContext.Companion.STATIC_DRAW


val BufferStorage.native
    get() = when (this) {
        DynamicBuffer -> DYNAMIC_DRAW
        ClientBuffer -> DYNAMIC_COPY
        ServerBuffer -> STATIC_DRAW
    }
