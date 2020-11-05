package graphics

import extensions.native
import org.khronos.webgl.DOMString
import org.khronos.webgl.Int8Array
import org.khronos.webgl.WebGL2RenderingContext
import org.khronos.webgl.WebGL2RenderingContext.Companion.ACTIVE_UNIFORMS
import org.khronos.webgl.WebGL2RenderingContext.Companion.ACTIVE_UNIFORM_BLOCKS
import org.khronos.webgl.WebGLFramebuffer
import org.w3c.dom.HTMLCanvasElement


actual class Device(val context: WebGL2RenderingContext) {

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer()?.let { Buffer(this, it, kind, storage) } ?: return null
        buffer {
            context.bufferData(kind.native, size.toInt(), storage.native)
        }
        return buffer
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>? {
        val buffer = context.createBuffer()?.let { Buffer(this, it, kind, storage) } ?: return null
        buffer {
            context.bufferData(kind.native, Int8Array(data.toTypedArray()), storage.native)
        }
        return buffer
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

    actual fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>): Pipeline? {
        val pipeline = context.createProgram()?.let { Pipeline(this, it, vertexShader, fragmentShader) } ?: return null
        context.attachShader(pipeline.handle, vertexShader.handle)
        context.attachShader(pipeline.handle, fragmentShader.handle)
        context.linkProgram(pipeline.handle)
        val blockCount = context.getProgramParameter(pipeline.handle, ACTIVE_UNIFORM_BLOCKS) as Int
        for (blockIndex in 0 until blockCount) context.uniformBlockBinding(pipeline.handle, blockIndex, blockIndex)
        context.getProgramInfoLog(pipeline.handle)?.takeIf(String::isNotBlank)?.let { println("Shader info log:\n$it") }
        return pipeline
    }


    actual fun createDrawCommandBuffer(): DrawCommandBuffer? {
        return DrawCommandBuffer(this, mutableListOf())
    }

}