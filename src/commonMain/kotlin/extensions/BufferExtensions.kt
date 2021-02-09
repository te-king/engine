package extensions

import graphics.*
import math.*


fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Int3Array) = writeData(offset, data.array)
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float2Array) = writeData(offset, data.array)
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float3Array) = writeData(offset, data.array)
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float4Array) = writeData(offset, data.array)
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4Array) = writeData(offset, data.array)

fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Int3) = writeData(offset, data.toIntArray())
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float2) = writeData(offset, data.toFloatArray())
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float3) = writeData(offset, data.toFloatArray())
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float4) = writeData(offset, data.toFloatArray())
fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: Float4x4) = writeData(offset, data.toFloatArray())
