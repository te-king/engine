package extensions

import graphics.BufferStorage
import graphics.ClientBuffer
import graphics.DynamicBuffer
import graphics.ServerBuffer
import platform.Metal.MTLResourceStorageModeManaged
import platform.Metal.MTLResourceStorageModePrivate
import platform.Metal.MTLResourceStorageModeShared


val BufferStorage.native
    get() = when (this) {
        DynamicBuffer -> MTLResourceStorageModeShared
        ClientBuffer -> MTLResourceStorageModeShared
        ServerBuffer -> MTLResourceStorageModePrivate
    }
