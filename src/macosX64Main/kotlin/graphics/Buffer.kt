package graphics

import extensions.native
import kotlinx.cinterop.*
import platform.Metal.MTLBufferProtocol
import platform.posix.memcpy

actual class Buffer<K : BufferKind, S : BufferStorage>(actual val device: Device, val handle: MTLBufferProtocol, val kind: K, val storage: S) {

    actual inner class Context {
        val device get() = this@Buffer.device
        val handle get() = this@Buffer.handle
        val kind = this@Buffer.kind
        val storage = this@Buffer.storage.native
        val contents get() = this@Buffer.handle.contents()
    }

    actual inline operator fun invoke(context: Context.() -> Unit) = Context().context()

}


@ExperimentalUnsignedTypes
actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ByteArray) {
    data.usePinned {
        memcpy(contents?.reinterpret<ByteVar>() + offset, it.addressOf(0), data.size.toULong() * Byte.SIZE_BYTES.toUInt())
    }
}

@ExperimentalUnsignedTypes
actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ShortArray) {
    data.usePinned {
        memcpy(contents?.reinterpret<ByteVar>() + offset, it.addressOf(0), data.size.toULong() * Short.SIZE_BYTES.toUInt())
    }
}

@ExperimentalUnsignedTypes
actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: IntArray) {
    data.usePinned {
        memcpy(contents?.reinterpret<ByteVar>() + offset, it.addressOf(0), data.size.toULong() * Int.SIZE_BYTES.toUInt())
    }
}

@ExperimentalUnsignedTypes
actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: LongArray) {
    data.usePinned {
        memcpy(contents?.reinterpret<ByteVar>() + offset, it.addressOf(0), data.size.toULong() * Long.SIZE_BYTES.toUInt())
    }
}

@ExperimentalUnsignedTypes
actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: FloatArray) {
    data.usePinned {
        memcpy(contents?.reinterpret<ByteVar>() + offset, it.addressOf(0), data.size.toULong() * Float.SIZE_BYTES.toUInt())
    }
}

@ExperimentalUnsignedTypes
actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: DoubleArray) {
    data.usePinned {
        memcpy(contents?.reinterpret<ByteVar>() + offset, it.addressOf(0), data.size.toULong() * Double.SIZE_BYTES.toUInt())
    }
}