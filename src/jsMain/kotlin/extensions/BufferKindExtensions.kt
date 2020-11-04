package extensions

import graphics.BufferKind
import graphics.DataBuffer
import graphics.IndexBuffer
import graphics.VertexBuffer
import org.khronos.webgl.WebGL2RenderingContext


val BufferKind.native
    get() = when (this) {
        DataBuffer -> WebGL2RenderingContext.UNIFORM_BUFFER
        VertexBuffer -> WebGL2RenderingContext.ARRAY_BUFFER
        IndexBuffer -> WebGL2RenderingContext.ELEMENT_ARRAY_BUFFER
    }