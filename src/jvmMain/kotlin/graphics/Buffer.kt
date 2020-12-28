package graphics

import extensions.native
import org.lwjgl.opengl.GL46C.*
import java.nio.ByteBuffer


actual class Buffer<K : BufferKind, S : BufferStorage>(actual val device: Device, val id: Int, val kind: K, val storage: S) {

    actual inner class Context {
        val device get() = this@Buffer.device
        val id get() = this@Buffer.id
        val kind = this@Buffer.kind.native
        val storage = this@Buffer.storage.native
    }

    actual inline operator fun invoke(context: Context.() -> Unit) = Context().context()

}


actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ByteArray) {
    glNamedBufferSubData(id, offset, ByteBuffer.wrap(data))
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ShortArray) {
    glNamedBufferSubData(id, offset, data)
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: IntArray) {
    glNamedBufferSubData(id, offset, data)
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: LongArray) {
    glNamedBufferSubData(id, offset, data)
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: FloatArray) {
    glNamedBufferSubData(id, offset, data)
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: DoubleArray) {
    glNamedBufferSubData(id, offset, data)
}