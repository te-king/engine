package graphics

import extensions.native
import org.khronos.webgl.*


actual class Device(val context: WebGL2RenderingContext) {

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer() ?: return null
        context.bindBuffer(kind.native, buffer)
        context.bufferData(kind.native, size.toInt(), storage.native)
        context.bindBuffer(kind.native, null)
        return Buffer(this, buffer, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer() ?: return null
        context.bindBuffer(kind.native, buffer)
        context.bufferData(kind.native, Int8Array(data.toTypedArray()), storage.native)
        context.bindBuffer(kind.native, null)
        return Buffer(this, buffer, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ShortArray, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer() ?: return null
        context.bindBuffer(kind.native, buffer)
        context.bufferData(kind.native, Int16Array(data.toTypedArray()), storage.native)
        context.bindBuffer(kind.native, null)
        return Buffer(this, buffer, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: IntArray, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer() ?: return null
        context.bindBuffer(kind.native, buffer)
        context.bufferData(kind.native, Int32Array(data.toTypedArray()), storage.native)
        context.bindBuffer(kind.native, null)
        return Buffer(this, buffer, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: LongArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: FloatArray, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer() ?: return null
        context.bindBuffer(kind.native, buffer)
        context.bufferData(kind.native, Float32Array(data.toTypedArray()), storage.native)
        context.bindBuffer(kind.native, null)
        return Buffer(this, buffer, kind, storage)
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: DoubleArray, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer() ?: return null
        context.bindBuffer(kind.native, buffer)
        context.bufferData(kind.native, Float64Array(data.toTypedArray()), storage.native)
        context.bindBuffer(kind.native, null)
        return Buffer(this, buffer, kind, storage)
    }


    actual fun <F : TextureFormat> createTexture2d(width: Long, height: Long, levels: Long, format: F): Texture<F, Texture2d>? {
        val texture = context.createTexture()?.let { Texture(this, it, format, Texture2d) } ?: return null
        texture(0) {
            context.texStorage2D(this.kind, levels, this.format, width, height)
        }
        return texture
    }


    actual fun <K : ShaderKind> createShader(source: String, kind: K): Shader<K>? {
        val shader = context.createShader(kind.native)?.let { Shader(this, it, kind) } ?: return null
        context.shaderSource(shader.handle, source)
        context.compileShader(shader.handle)
        context.getShaderInfoLog(shader.handle)?.takeIf(String::isNotBlank)?.let { println("Shader info log:\n$it") }
        return shader
    }

    actual fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>, vararg mappings: Pair<String, Int>): Pipeline? {
        val pipeline = context.createProgram()?.let { Pipeline(this, it, vertexShader, fragmentShader) } ?: return null
        context.attachShader(pipeline.handle, vertexShader.handle)
        context.attachShader(pipeline.handle, fragmentShader.handle)
        context.linkProgram(pipeline.handle)

        for ((name, binding) in mappings) {
            val index = context.getUniformBlockIndex(pipeline.handle, name)
            context.uniformBlockBinding(pipeline.handle, index, binding)
        }

        context.getProgramInfoLog(pipeline.handle)?.takeIf(String::isNotBlank)?.let { println("Shader info log:\n$it") }
        return pipeline
    }


    actual fun createDrawCommandBuffer(deviceState: DeviceState): DrawCommandBuffer? {

        val commands = mutableListOf<() -> Unit>()

        commands.add {
            context.bindFramebuffer(WebGL2RenderingContext.READ_FRAMEBUFFER, deviceState.readFramebuffer?.handle)
            context.bindFramebuffer(WebGL2RenderingContext.DRAW_FRAMEBUFFER, deviceState.writeFramebuffer?.handle)
        }

        if (deviceState.cullFunc != null)
            commands.add {
                context.enable(WebGL2RenderingContext.CULL_FACE)
                context.cullFace(deviceState.cullFunc.native)
                context.frontFace(deviceState.winding.native)
            }
        else
            commands.add {
                context.disable(WebGL2RenderingContext.CULL_FACE)
            }

        if (deviceState.blendFunction != null)
            commands.add {
                context.enable(WebGL2RenderingContext.BLEND)
                // TODO
            }
        else
            commands.add {
                context.disable(WebGL2RenderingContext.BLEND)
            }

        if (deviceState.depthFunction != null)
            commands.add {
                context.enable(WebGL2RenderingContext.DEPTH_TEST)
                context.depthFunc(deviceState.depthFunction.native)
            }
        else
            commands.add {
                context.disable(WebGL2RenderingContext.DEPTH_TEST)
            }

        return DrawCommandBuffer(this, commands)

    }

}