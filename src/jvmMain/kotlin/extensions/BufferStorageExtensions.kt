package extensions

import graphics.BufferStorage
import graphics.ClientBuffer
import graphics.DynamicBuffer
import graphics.ServerBuffer

import org.lwjgl.opengl.GL46C.*


val BufferStorage.native
    get() = when (this) {
        DynamicBuffer -> GL_DYNAMIC_STORAGE_BIT
        ClientBuffer -> GL_CLIENT_STORAGE_BIT
        ServerBuffer -> 0
    }
