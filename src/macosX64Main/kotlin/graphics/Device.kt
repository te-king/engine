package graphics

import extensions.native
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.toCValues
import kotlinx.cinterop.usePinned
import platform.Metal.MTLDeviceProtocol

actual class Device(val mtl: MTLDeviceProtocol) {

    val queue = mtl.newCommandQueue() ?: error("Cannot create command queue")

    @ExperimentalUnsignedTypes
    actual fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>? {
        val handle = mtl.newBufferWithLength(size.toULong(), storage.native) ?: return null
        return Buffer(this, handle, kind, storage)
    }

    @ExperimentalUnsignedTypes
    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>? {
        data.usePinned {
            val handle = mtl.newBufferWithBytes(it.addressOf(0), data.size.toULong() * Byte.SIZE_BYTES.toUInt(), storage.native) ?: return null
            return Buffer(this, handle, kind, storage)
        }
    }

    @ExperimentalUnsignedTypes
    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ShortArray, kind: K, storage: S): Buffer<K, S>? {
        data.usePinned {
            val handle = mtl.newBufferWithBytes(it.addressOf(0), data.size.toULong() * Short.SIZE_BYTES.toUInt(), storage.native) ?: return null
            return Buffer(this, handle, kind, storage)
        }
    }

    @ExperimentalUnsignedTypes
    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: IntArray, kind: K, storage: S): Buffer<K, S>? {
        data.usePinned {
            val handle = mtl.newBufferWithBytes(it.addressOf(0), data.size.toULong() * Int.SIZE_BYTES.toUInt(), storage.native) ?: return null
            return Buffer(this, handle, kind, storage)
        }
    }

    @ExperimentalUnsignedTypes
    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: LongArray, kind: K, storage: S): Buffer<K, S>? {
        data.usePinned {
            val handle = mtl.newBufferWithBytes(it.addressOf(0), data.size.toULong() * Long.SIZE_BYTES.toUInt(), storage.native) ?: return null
            return Buffer(this, handle, kind, storage)
        }
    }

    @ExperimentalUnsignedTypes
    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: FloatArray, kind: K, storage: S): Buffer<K, S>? {
        data.usePinned {
            val handle = mtl.newBufferWithBytes(it.addressOf(0), data.size.toULong() * Float.SIZE_BYTES.toUInt(), storage.native) ?: return null
            return Buffer(this, handle, kind, storage)
        }
    }

    @ExperimentalUnsignedTypes
    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: DoubleArray, kind: K, storage: S): Buffer<K, S>? {
        data.usePinned {
            val handle = mtl.newBufferWithBytes(it.addressOf(0), data.size.toULong() * Double.SIZE_BYTES.toUInt(), storage.native) ?: return null
            return Buffer(this, handle, kind, storage)
        }
    }

    actual fun <F : TextureFormat> createTexture2d(
        width: Long,
        height: Long,
        levels: Long,
        format: F
    ): Texture<F, Texture2d>? {
        TODO("Not yet implemented")
    }

    actual fun <K : ShaderKind> createShader(source: String, kind: K): Shader<K>? {
        TODO("Not yet implemented")
    }

    actual fun createPipeline(
        vertexShader: Shader<VertexShader>,
        fragmentShader: Shader<FragmentShader>
    ): Pipeline? {
        TODO("Not yet implemented")
    }

    actual fun createDrawCommandBuffer(): DrawCommandBuffer? {
        TODO("Not yet implemented")
    }

}