package extensions

import graphics.BufferStorage
import graphics.ClientStorage
import graphics.DynamicStorage
import graphics.ServerStorage

import org.lwjgl.opengl.GL46C.*


val BufferStorage.native
    get() = when (this) {
        DynamicStorage -> GL_DYNAMIC_STORAGE_BIT
        ClientStorage -> GL_CLIENT_STORAGE_BIT
        ServerStorage -> 0
    }
