package graphics

import math.Color
import kotlin.reflect.KClass

actual class DrawCommandBuffer(actual val device: Device, val commands: MutableList<() -> Unit>) {

    actual fun bindDataBuffer(index: Int, buffer: Buffer<DataKind, *>?) {

    }

    actual fun bindVertexBuffer(index: Int, buffer: Buffer<VertexKind, *>?, type: KClass<*>, offset: Long, stride: Int) {

    }

    actual fun bindIndexBuffer(buffer: Buffer<IndexKind, *>?) {

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