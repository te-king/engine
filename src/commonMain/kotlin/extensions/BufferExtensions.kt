package extensions

import graphics.*
import math.*

fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3Array) = writeData(offset, data.array)
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2Array) = writeData(offset, data.array)
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3Array) = writeData(offset, data.array)
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4Array) = writeData(offset, data.array)
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4Array) = writeData(offset, data.array)
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3) = writeData(offset, data.toIntArray())
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2) = writeData(offset, data.toFloatArray())
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3) = writeData(offset, data.toFloatArray())
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4) = writeData(offset, data.toFloatArray())
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4) = writeData(offset, data.toFloatArray())

fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3Array) = writeData(offset, data.array)
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2Array) = writeData(offset, data.array)
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3Array) = writeData(offset, data.array)
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4Array) = writeData(offset, data.array)
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4Array) = writeData(offset, data.array)
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3) = writeData(offset, data.toIntArray())
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2) = writeData(offset, data.toFloatArray())
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3) = writeData(offset, data.toFloatArray())
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4) = writeData(offset, data.toFloatArray())
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4) = writeData(offset, data.toFloatArray())

fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3Array) = writeData(offset, data.array)
fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3) = writeData(offset, data.toIntArray())