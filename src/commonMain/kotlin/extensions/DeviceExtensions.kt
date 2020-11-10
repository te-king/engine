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


fun Device.createMeshBufferObject(mesh: Mesh): MeshBufferObject? {

    val vertices = mesh.verticesList.mapIndexedNotNull { index, float4Array ->
        if (float4Array.size == 0) return@mapIndexedNotNull null
        val buffer = createBuffer(float4Array, VertexBuffer, ServerBuffer) ?: return null
        index to VertexBufferObject(buffer, Float4::class, 0, 0)
    }.toMap()

    val indices = mesh.indiciesList.map {
        val buffer = createBuffer(it, IndexBuffer, ServerBuffer) ?: return null
        val count = it.size
        val kind = PrimitiveType.Triangles
        IndexBufferObject(buffer, count, kind)
    }

    return MeshBufferObject(vertices, indices)

}