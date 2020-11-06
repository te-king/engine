package extensions

import graphics.*
import math.*


fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Int3Array, kind: K, storage: S) = createBuffer(data.array, kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float2Array, kind: K, storage: S) = createBuffer(data.array, kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float3Array, kind: K, storage: S) = createBuffer(data.array, kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float4Array, kind: K, storage: S) = createBuffer(data.array, kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float4x4Array, kind: K, storage: S) = createBuffer(data.array, kind, storage)

fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Int3, kind: K, storage: S) = createBuffer(data.toIntArray(), kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float2, kind: K, storage: S) = createBuffer(data.toFloatArray(), kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float3, kind: K, storage: S) = createBuffer(data.toFloatArray(), kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float4, kind: K, storage: S) = createBuffer(data.toFloatArray(), kind, storage)
fun <K : BufferKind, S : BufferStorage> Device.createBuffer(data: Float4x4, kind: K, storage: S) = createBuffer(data.toFloatArray(), kind, storage)

fun <F : TextureFormat> Device.createImage2d(width: Long, height: Long, format: F) = createTexture2d(width, height, 1, format)

expect inline fun Device.draw(state: DeviceState, crossinline fn: DrawCommandBuffer.() -> Unit)