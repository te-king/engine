package extensions

import graphics.BufferKind
import graphics.DataKind
import graphics.IndexKind
import graphics.VertexKind

import org.lwjgl.opengl.GL46C.*


val BufferKind.native
    get() = when (this) {
        DataKind -> GL_UNIFORM_BUFFER
        VertexKind -> GL_ARRAY_BUFFER
        IndexKind -> GL_ELEMENT_ARRAY_BUFFER
    }