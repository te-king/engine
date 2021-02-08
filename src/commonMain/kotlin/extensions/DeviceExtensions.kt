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


fun Device.createMeshBufferObject(mesh: Mesh): MeshBufferObject? {

    val vertices = mesh.vertices.mapIndexedNotNull { index, it ->
        if (it.none()) return@mapIndexedNotNull null
        val data = it.toList()
        val buffer = createBuffer(data.toFloat4Array(), VertexBuffer, ServerBuffer) ?: return@mapIndexedNotNull null
        index to VertexBufferObject(buffer, Float4::class, 0, 0)
    }.toMap()

    val indices = mesh.indicies.mapNotNull {
        if (it.none()) return@mapNotNull null
        val data = it.toList()
        val buffer = createBuffer(data.toIntArray(), IndexBuffer, ServerBuffer) ?: return@mapNotNull null
        val count = data.size
        val kind = PrimitiveType.Triangles
        IndexBufferObject(buffer, count, kind)
    }

    return MeshBufferObject(vertices, indices.toList())

}