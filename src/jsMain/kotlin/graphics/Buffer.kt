package graphics

import extensions.native
import org.khronos.webgl.*


actual class Buffer<K : BufferKind, S : BufferStorage>(actual val device: Device, val handle: WebGLBuffer, val kind: K, val storage: S) {

    actual inner class Context {
        val device get() = this@Buffer.device
        val handle get() = this@Buffer.handle
        val kind = this@Buffer.kind.native
        val storage = this@Buffer.storage.native
    }

    actual inline operator fun invoke(context: Context.() -> Unit) {
        Context().apply {
            device.context.bindBuffer(kind, handle)
            context()
            device.context.bindBuffer(kind, null)
        }
    }

}


actual fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: ByteArray) {
    device.context.bufferSubData(kind, offset.toInt(), Int8Array(data.toTypedArray()))
}

actual fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: ShortArray) {
    device.context.bufferSubData(kind, offset.toInt(), Int16Array(data.toTypedArray()))
}

actual fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: IntArray) {
    device.context.bufferSubData(kind, offset.toInt(), Int32Array(data.toTypedArray()))
}

actual fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: LongArray) {
    TODO()
//    device.context.bufferSubData(kind, offset.toInt(), Int64Array(data.toTypedArray()))
}

actual fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray) {
    device.context.bufferSubData(kind, offset.toInt(), Float32Array(data.toTypedArray()))
}

actual fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray) {
    device.context.bufferSubData(kind, offset.toInt(), Float64Array(data.toTypedArray()))
}