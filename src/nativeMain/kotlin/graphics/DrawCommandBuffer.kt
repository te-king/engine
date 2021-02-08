package graphics

import math.Color
import kotlin.reflect.KClass


actual class DrawCommandBuffer(actual val device: Device) {

    actual fun bindDataBuffer(index: Int, buffer: Buffer<DataBuffer, *>?) {
        TODO("Not yet implemented")
    }

    actual fun bindVertexBuffer(index: Int, buffer: Buffer<VertexBuffer, *>?, type: KClass<*>, offset: Long, stride: Int) {
        TODO("Not yet implemented")
    }

    actual fun bindIndexBuffer(buffer: Buffer<IndexBuffer, *>?) {
        TODO("Not yet implemented")
    }

    actual fun bindPipeline(pipeline: Pipeline?) {
        TODO("Not yet implemented")
    }


    actual fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) {
        TODO("Not yet implemented")
    }

    actual fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int) {
        TODO("Not yet implemented")
    }


    actual fun clearFramebuffer(color: Color) {
        TODO("Not yet implemented")
    }

    actual fun submit() {
        TODO("Not yet implemented")
    }

}