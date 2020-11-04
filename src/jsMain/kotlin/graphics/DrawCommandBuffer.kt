package graphics

import extensions.native
import math.*
import org.khronos.webgl.WebGL2RenderingContext.Companion.COLOR_BUFFER_BIT
import org.khronos.webgl.WebGL2RenderingContext.Companion.DEPTH_BUFFER_BIT
import org.khronos.webgl.WebGL2RenderingContext.Companion.ELEMENT_ARRAY_BUFFER
import org.khronos.webgl.WebGL2RenderingContext.Companion.FLOAT
import org.khronos.webgl.WebGL2RenderingContext.Companion.INT
import org.khronos.webgl.WebGL2RenderingContext.Companion.STENCIL_BUFFER_BIT
import org.khronos.webgl.WebGL2RenderingContext.Companion.UNIFORM_BUFFER
import org.khronos.webgl.WebGL2RenderingContext.Companion.UNSIGNED_INT
import kotlin.reflect.KClass


actual class DrawCommandBuffer(actual val device: Device, private val buffer: MutableList<() -> Unit>) {

    actual fun bindDataBuffer(index: Int, buffer: Buffer<DataBuffer, *>?) {
        device.context.bindBufferBase(UNIFORM_BUFFER, index, buffer?.handle)
    }

    actual fun bindVertexBuffer(index: Int, buffer: Buffer<VertexBuffer, *>?, type: KClass<*>, offset: Long, stride: Int) {
        when (buffer) {
            null -> device.context.disableVertexAttribArray(index)
            else -> device.context.enableVertexAttribArray(index)
        }
        (buffer ?: return) {
            when (type) {
                Float::class -> device.context.vertexAttribPointer(index, 1, FLOAT, false, stride, offset.toInt())
                Float2::class -> device.context.vertexAttribPointer(index, 2, FLOAT, false, stride, offset.toInt())
                Float3::class -> device.context.vertexAttribPointer(index, 3, FLOAT, false, stride, offset.toInt())
                Float4::class -> device.context.vertexAttribPointer(index, 4, FLOAT, false, stride, offset.toInt())
                Int::class -> device.context.vertexAttribIPointer(index, 1, INT, stride.toLong(), offset)
                Int2::class -> device.context.vertexAttribIPointer(index, 2, INT, stride.toLong(), offset)
                Int3::class -> device.context.vertexAttribIPointer(index, 3, INT, stride.toLong(), offset)
                Int4::class -> device.context.vertexAttribIPointer(index, 4, INT, stride.toLong(), offset)
            }
        }
    }

    actual fun bindIndexBuffer(buffer: Buffer<IndexBuffer, *>?) {
        device.context.bindBuffer(ELEMENT_ARRAY_BUFFER, buffer?.handle)
    }

    actual fun bindPipeline(pipeline: Pipeline?) {
        device.context.useProgram(pipeline?.handle)
    }


    actual fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) {
        device.context.drawArrays(primitiveType.native, 0, vertexCount)
    }

    actual fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int) {
        device.context.drawElements(primitiveType.native, indexCount, UNSIGNED_INT, 0)
    }


    actual fun clearFramebuffer(color: Color) {
        device.context.clearColor(color.r, color.g, color.b, color.a)
        device.context.clear(COLOR_BUFFER_BIT or DEPTH_BUFFER_BIT or STENCIL_BUFFER_BIT)
    }

    actual fun submit() {
        for (it in buffer) it()
    }

}