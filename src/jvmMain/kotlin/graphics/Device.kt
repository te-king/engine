package graphics

import extensions.native
import extensions.stage
import kotlinx.coroutines.CoroutineDispatcher
import org.lwjgl.opengl.GL46C.*
import java.nio.ByteBuffer
import java.nio.ShortBuffer


actual class Device(val context: CoroutineDispatcher) {

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>? {
        val id = glCreateBuffers().takeUnless { it == 0 } ?: return null
        glNamedBufferStorage(id, size, storage.native)
        return Buffer(this, id, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>? {
        val id = glCreateBuffers().takeUnless { it == 0 } ?: return null
        glNamedBufferStorage(id, ByteBuffer.wrap(data), storage.native)
        return Buffer(this, id, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ShortArray, kind: K, storage: S): Buffer<K, S>? {
        val id = glCreateBuffers().takeUnless { it == 0 } ?: return null
        glNamedBufferStorage(id, data, storage.native)
        return Buffer(this, id, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: IntArray, kind: K, storage: S): Buffer<K, S>? {
        val id = glCreateBuffers().takeUnless { it == 0 } ?: return null
        glNamedBufferStorage(id, data, storage.native)
        return Buffer(this, id, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: LongArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: FloatArray, kind: K, storage: S): Buffer<K, S>? {
        val id = glCreateBuffers().takeUnless { it == 0 } ?: return null
        glNamedBufferStorage(id, data, storage.native)
        return Buffer(this, id, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: DoubleArray, kind: K, storage: S): Buffer<K, S>? {
        val id = glCreateBuffers().takeUnless { it == 0 } ?: return null
        glNamedBufferStorage(id, data, storage.native)
        return Buffer(this, id, kind, storage)
    }

    actual fun <F : TextureFormat> createTexture2d(width: Long, height: Long, levels: Long, format: F): Texture<F, Texture2d>? {
        TODO("Not yet implemented")
    }

    actual fun <K : ShaderKind> createShader(source: String, kind: K): Shader<K>? {
        val id = glCreateShaderProgramv(kind.native, source).takeUnless { it == 0 } ?: return null
        return Shader(this, id, kind)
    }

    actual fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>): Pipeline? {
        val id = glCreateProgramPipelines().takeUnless { it == 0 } ?: return null
        glUseProgramStages(id, VertexShader.stage, vertexShader.id)
        glUseProgramStages(id, FragmentShader.stage, fragmentShader.id)
        return Pipeline(this, id, vertexShader, fragmentShader)
    }

    actual fun createDrawCommandBuffer(): DrawCommandBuffer? {
        return DrawCommandBuffer(this)
    }

}