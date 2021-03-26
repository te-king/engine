package extensions

import graphics.*
import math.*
import kotlin.jvm.JvmName


@JvmName("writeDataInt3")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3Array) = writeData(offset, data.array)

@JvmName("writeDataFloat2")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2Array) = writeData(offset, data.array)

@JvmName("writeDataFloat3")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3Array) = writeData(offset, data.array)

@JvmName("writeDataFloat4")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4Array) = writeData(offset, data.array)

@JvmName("writeDataFloat4x4")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4Array) = writeData(offset, data.array)

@JvmName("writeDataInt3")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3) = writeData(offset, data.toIntArray())

@JvmName("writeDataFloat2")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2) = writeData(offset, data.toFloatArray())

@JvmName("writeDataFloat3")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3) = writeData(offset, data.toFloatArray())

@JvmName("writeDataFloat4")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4) = writeData(offset, data.toFloatArray())

@JvmName("writeDataFloat4x4")
fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4) = writeData(offset, data.toFloatArray())


@JvmName("writeVertexFloat2")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2Array) = writeData(offset, data.array)

@JvmName("writeVertexFloat3")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3Array) = writeData(offset, data.array)

@JvmName("writeVertexFloat4")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4Array) = writeData(offset, data.array)

@JvmName("writeVertexFloat4x4")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4Array) = writeData(offset, data.array)

@JvmName("writeVertexFloat2")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float2) = writeData(offset, data.toFloatArray())

@JvmName("writeVertexFloat3")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float3) = writeData(offset, data.toFloatArray())

@JvmName("writeVertexFloat4")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4) = writeData(offset, data.toFloatArray())

@JvmName("writeVertexFloat4x4")
fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4) = writeData(offset, data.toFloatArray())


@JvmName("writeIndexInt3")
fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3Array) = writeData(offset, data.array)

@JvmName("writeIndexInt3")
fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: Int3) = writeData(offset, data.toIntArray())