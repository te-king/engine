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

    actual fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>, vararg mappings: Pair<String, Int>): Pipeline? =
        runBlocking(context) {
            val id = glCreateProgramPipelines().takeUnless { it == 0 } ?: return@runBlocking null
            glUseProgramStages(id, VertexShader.stage, vertexShader.id)
            glUseProgramStages(id, FragmentShader.stage, fragmentShader.id)
            if (mappings.isNotEmpty()) println("Mappings are ignored in opengl 4.6")
            return@runBlocking Pipeline(this@Device, id, vertexShader, fragmentShader)
        }

    actual fun createDrawCommandBuffer(deviceState: DeviceState): DrawCommandBuffer? {
        val commands = mutableListOf<() -> Unit>()

        commands.add {
            glBindFramebuffer(GL_READ_FRAMEBUFFER, deviceState.readFramebuffer?.id ?: 0)
            glBindFramebuffer(GL_DRAW_FRAMEBUFFER, deviceState.writeFramebuffer?.id ?: 0)
        }

//            if (deviceState.cullFunc != null)
//                commands.add {
//                    context.enable(WebGL2RenderingContext.CULL_FACE)
//                    context.cullFace(deviceState.cullFunc.native)
//            glFrontFace(state.winding.native)
//                }
//            else
//                commands.add {
//                    context.disable(WebGL2RenderingContext.CULL_FACE)
//                }
//
//            if (deviceState.blendFunction != null)
//                commands.add {
//                    context.enable(WebGL2RenderingContext.BLEND)
//                    // TODO
//                }
//            else
//                commands.add {
//                    context.disable(WebGL2RenderingContext.BLEND)
//                }
//
//            if (deviceState.depthFunction != null)
//                commands.add {
//                    context.enable(WebGL2RenderingContext.DEPTH_TEST)
//                    context.depthFunc(deviceState.depthFunction.native)
//                }
//            else
//                commands.add {
//                    context.disable(WebGL2RenderingContext.DEPTH_TEST)
//                }

        return DrawCommandBuffer(this@Device, commands)

    }

}