package extensions

import graphics.BufferKind
import graphics.DataBuffer
import graphics.IndexBuffer
import graphics.VertexBuffer

import org.lwjgl.opengl.GL46C.*


val BufferKind.native
    get() = when (this) {
        DataBuffer -> GL_UNIFORM_BUFFER
        VertexBuffer -> GL_ARRAY_BUFFER
        IndexBuffer -> GL_ELEMENT_ARRAY_BUFFER
    }