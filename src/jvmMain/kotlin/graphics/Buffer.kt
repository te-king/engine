package graphics

import extensions.native
import org.lwjgl.opengl.GL46C.*
import java.nio.ByteBuffer


actual class Buffer<K : BufferKind, S : BufferStorage>(actual val device: Device, val id: Int, val kind: K, val storage: S) {

    actual inner class Context {
        val device get() = this@Buffer.device
        val id get() = this@Buffer.id
    }

}


@JvmName("invokeData")
actual inline operator fun Buffer<DataKind, DynamicStorage>.invoke(context: Buffer<DataKind, DynamicStorage>.Context.() -> Unit) =
    Context().let(context)

@JvmName("writeDataByte")
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: ByteArray) = glNamedBufferSubData(id, offset, ByteBuffer.wrap(data))

@JvmName("writeDataShort")
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: ShortArray) = glNamedBufferSubData(id, offset, data)

@JvmName("writeDataInt")
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: IntArray) = glNamedBufferSubData(id, offset, data)

@JvmName("writeDataLong")
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: LongArray) = glNamedBufferSubData(id, offset, data)

@JvmName("writeDataFloat")
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray) = glNamedBufferSubData(id, offset, data)

@JvmName("writeDataDouble")
actual fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray) = glNamedBufferSubData(id, offset, data)


@JvmName("invokeVertex")
actual inline operator fun Buffer<VertexKind, DynamicStorage>.invoke(context: Buffer<VertexKind, DynamicStorage>.Context.() -> Unit) =
    Context().let(context)

@JvmName("writeVertexFloat")
actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray) = glNamedBufferSubData(id, offset, data)

@JvmName("writeVertexDouble")
actual fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray) = glNamedBufferSubData(id, offset, data)


@JvmName("invokeIndex")
actual inline operator fun Buffer<IndexKind, DynamicStorage>.invoke(context: Buffer<IndexKind, DynamicStorage>.Context.() -> Unit) =
    Context().let(context)

@JvmName("writeIndexInt")
actual fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: IntArray) = glNamedBufferSubData(id, offset, data)