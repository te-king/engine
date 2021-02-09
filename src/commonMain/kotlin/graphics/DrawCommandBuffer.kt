package graphics

import math.Color
import kotlin.reflect.KClass


expect class DrawCommandBuffer {

    val device: Device


    fun bindDataBuffer(index: Int, buffer: Buffer<DataKind, *>?)

    fun bindVertexBuffer(index: Int, buffer: Buffer<DataKind, *>?, type: KClass<*>, offset: Long, stride: Int)

    fun bindIndexBuffer(buffer: Buffer<IndexKind, *>?)

    fun bindPipeline(pipeline: Pipeline?)


    fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int)

    fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int)


    fun clearFramebuffer(color: Color)

    fun submit()

}