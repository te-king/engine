package graphics

import extensions.native
import extensions.stage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.lwjgl.opengl.GL46C.*
import java.nio.ByteBuffer
import java.nio.ShortBuffer


actual class Device(val context: CoroutineDispatcher) {

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>? =
        runBlocking(context) {
            val id = glCreateBuffers().takeUnless { it == 0 } ?: return@runBlocking null
            glNamedBufferStorage(id, size, storage.native)
            return@runBlocking Buffer(this@Device, id, kind, storage)
        }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>? =
        runBlocking(context) {
            val id = glCreateBuffers().takeUnless { it == 0 } ?: return@runBlocking null
            glNamedBufferStorage(id, ByteBuffer.wrap(data), storage.native)
            return@runBlocking Buffer(this@Device, id, kind, storage)
        }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ShortArray, kind: K, storage: S): Buffer<K, S>? =
        runBlocking(context) {
            val id = glCreateBuffers().takeUnless { it == 0 } ?: return@runBlocking null
            glNamedBufferStorage(id, data, storage.native)
            return@runBlocking Buffer(this@Device, id, kind, storage)
        }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: IntArray, kind: K, storage: S): Buffer<K, S>? =
        runBlocking(context) {
            val id = glCreateBuffers().takeUnless { it == 0 } ?: return@runBlocking null
            glNamedBufferStorage(id, data, storage.native)
            return@runBlocking Buffer(this@Device, id, kind, storage)
        }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: LongArray, kind: K, storage: S): Buffer<K, S>? =
        runBlocking(context) {
            TODO("Not yet implemented")
        }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: FloatArray, kind: K, storage: S): Buffer<K, S>? =
        runBlocking(context) {
            val id = glCreateBuffers().takeUnless { it == 0 } ?: return@runBlocking null
            glNamedBufferStorage(id, data, storage.native)
            return@runBlocking Buffer(this@Device, id, kind, storage)
        }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: DoubleArray, kind: K, storage: S): Buffer<K, S>? =
        runBlocking(context) {
            val id = glCreateBuffers().takeUnless { it == 0 } ?: return@runBlocking null
            glNamedBufferStorage(id, data, storage.native)
            return@runBlocking Buffer(this@Device, id, kind, storage)
        }

    actual fun <F : TextureFormat> createTexture2d(width: Long, height: Long, levels: Long, format: F): Texture<F, Texture2d>? {
        TODO("Not yet implemented")
    }

    actual fun <K : ShaderKind> createShader(source: String, kind: K): Shader<K>? =
        runBlocking(context) {
            val id = glCreateShaderProgramv(kind.native, source).takeUnless { it == 0 } ?: return@runBlocking null
            return@runBlocking Shader(this@Device, id, kind)
        }

    actual fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>): Pipeline? =
        runBlocking(context) {
            val id = glCreateProgramPipelines().takeUnless { it == 0 } ?: return@runBlocking null
            glUseProgramStages(id, VertexShader.stage, vertexShader.id)
            glUseProgramStages(id, FragmentShader.stage, fragmentShader.id)
            return@runBlocking Pipeline(this@Device, id, vertexShader, fragmentShader)
        }

    actual fun createDrawCommandBuffer(): DrawCommandBuffer? =
        runBlocking(context) {
            return@runBlocking DrawCommandBuffer(this@Device)
        }

}


actual inline fun Device.draw(state: DeviceState, crossinline fn: DrawCommandBuffer.() -> Unit) {
    val commandBuffer = createDrawCommandBuffer()?.also(fn) ?: return

    glBindFramebuffer(GL_READ_FRAMEBUFFER, state.readFramebuffer?.id ?: 0)
    glBindFramebuffer(GL_DRAW_FRAMEBUFFER, state.writeFramebuffer?.id ?: 0)

    glFrontFace(state.winding.native)

//
//    if (state.cullFunc != null) {
//        context.enable(CULL_FACE)
//        context.cullFace(state.cullFunc.native)
//    } else {
//        context.disable(CULL_FACE)
//    }
//
//    if (state.blendFunction != null) {
//        context.enable(BLEND)
//        // TODO
//    } else {
//        context.disable(BLEND)
//    }
//
//    if (state.depthFunction != null) {
//        context.enable(DEPTH_TEST)
//        context.depthFunc(state.depthFunction.native)
//    } else {
//        context.disable(DEPTH_TEST)
//    }

    commandBuffer.submit()
}