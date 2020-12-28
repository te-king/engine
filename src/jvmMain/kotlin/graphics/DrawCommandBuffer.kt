package graphics

import math.Color
import kotlin.reflect.KClass

actual class DrawCommandBuffer(actual val device: Device) {

    actual fun bindDataBuffer(index: Int, buffer: Buffer<DataBuffer, *>?) {

    }

    actual fun bindVertexBuffer(index: Int, buffer: Buffer<VertexBuffer, *>?, type: KClass<*>, offset: Long, stride: Int) {

    }

    actual fun bindIndexBuffer(buffer: Buffer<IndexBuffer, *>?) {

    }

    actual fun bindPipeline(pipeline: Pipeline?) {

    }


    actual fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) {

    }

    actual fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int) {

    }


    actual fun clearFramebuffer(color: Color) {

    }

    actual fun submit() {

    }

}