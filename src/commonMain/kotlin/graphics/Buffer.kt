package graphics

import math.*


expect class Buffer<K : BufferKind, M : BufferStorage> {

    val device: Device


    inner class Context

    inline operator fun invoke(context: Context.() -> Unit)

}

expect fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ByteArray)
expect fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ShortArray)
expect fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: IntArray)
expect fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: LongArray)
expect fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: FloatArray)
expect fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: DoubleArray)

fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Int3Array) = writeData(offset, data.array)
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float2Array) = writeData(offset, data.array)
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float3Array) = writeData(offset, data.array)
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float4Array) = writeData(offset, data.array)
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float4x4Array) = writeData(offset, data.array)

fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Int3) = writeData(offset, data.toIntArray())
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float2) = writeData(offset, data.toFloatArray())
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float3) = writeData(offset, data.toFloatArray())
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float4) = writeData(offset, data.toFloatArray())
fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: Float4x4) = writeData(offset, data.toFloatArray())