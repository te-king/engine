package extensions

import graphics.BufferKind
import graphics.DataKind
import graphics.IndexKind
import graphics.VertexKind
import org.khronos.webgl.WebGL2RenderingContext


val BufferKind.native
    get() = when (this) {
        DataKind -> WebGL2RenderingContext.UNIFORM_BUFFER
        VertexKind -> WebGL2RenderingContext.ARRAY_BUFFER
        IndexKind -> WebGL2RenderingContext.ELEMENT_ARRAY_BUFFER
    }