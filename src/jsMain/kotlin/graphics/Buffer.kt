package graphics

import extensions.native
import org.khronos.webgl.*
import org.khronos.webgl.WebGL2RenderingContext.Companion.ARRAY_BUFFER
import org.khronos.webgl.WebGL2RenderingContext.Companion.ELEMENT_ARRAY_BUFFER


actual class Buffer<K : BufferKind, S : BufferStorage>(actual val device: Device, val handle: WebGLBuffer, val kind: K, val storage: S) {

    actual inner class Context {
        val device get() = this@Buffer.device
        val handle get() = this@Buffer.handle
        val kind = this@Buffer.kind.native
        val storage = this@Buffer.storage.native
    }

}


actual inline operator fun Buffer<DataKind, DynamicStorage>.invoke(context: Buffer<DataKind, DynamicStorage>.Context.() -> Unit) {
    device.context.bindBuffer(ARRAY_BUFFER, handle)
    Context().let(context)
    device.context.bindBuffer(ARRAY_BUFFER, null)
}

actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: ByteArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Int8Array(data.toTypedArray()))
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: ShortArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Int16Array(data.toTypedArray()))
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: IntArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Int32Array(data.toTypedArray()))
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: LongArray): Unit = TODO()//    device.context.bufferSubData(kind, offset.toInt(), Int64Array(data.toTypedArray()))
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Float32Array(data.toTypedArray()))
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Float64Array(data.toTypedArray()))


actual inline operator fun Buffer<VertexKind, DynamicStorage>.invoke(context: Buffer<VertexKind, DynamicStorage>.Context.() -> Unit) {
    device.context.bindBuffer(ARRAY_BUFFER, handle)
    Context().let(context)
    device.context.bindBuffer(ARRAY_BUFFER, null)
}

actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: ByteArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Int8Array(data.toTypedArray()))
actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: ShortArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Int16Array(data.toTypedArray()))
actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: IntArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Int32Array(data.toTypedArray()))
actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: LongArray): Unit = TODO()//    device.context.bufferSubData(kind, offset.toInt(), Int64Array(data.toTypedArray()))
actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Float32Array(data.toTypedArray()))
actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray) = device.context.bufferSubData(ARRAY_BUFFER, offset.toInt(), Float64Array(data.toTypedArray()))


actual inline operator fun Buffer<IndexKind, DynamicStorage>.invoke(context: Buffer<IndexKind, DynamicStorage>.Context.() -> Unit) {
    device.context.bindBuffer(ELEMENT_ARRAY_BUFFER, handle)
    Context().let(context)
    device.context.bindBuffer(ELEMENT_ARRAY_BUFFER, null)
}

//actual inline fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: ByteArray) = device.context.bufferSubData(ELEMENT_ARRAY_BUFFER, offset.toInt(), Int8Array(data.toTypedArray()))
//actual inline fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: ShortArray) = device.context.bufferSubData(ELEMENT_ARRAY_BUFFER, offset.toInt(), Int16Array(data.toTypedArray()))
actual fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: IntArray) = device.context.bufferSubData(ELEMENT_ARRAY_BUFFER, offset.toInt(), Int32Array(data.toTypedArray()))
//actual inline fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: LongArray): Unit = TODO()//    device.context.bufferSubData(kind, offset.toInt(), Int64Array(data.toTypedArray()))
//actual inline fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray) = device.context.bufferSubData(ELEMENT_ARRAY_BUFFER, offset.toInt(), Float32Array(data.toTypedArray()))
//actual inline fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray) = device.context.bufferSubData(ELEMENT_ARRAY_BUFFER, offset.toInt(), Float64Array(data.toTypedArray()))
