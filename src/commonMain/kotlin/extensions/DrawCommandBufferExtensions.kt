package extensions

import graphics.Buffer
import graphics.DataBuffer
import graphics.DrawCommandBuffer


fun DrawCommandBuffer.bindCameraTransformBuffer(buffer: Buffer<DataBuffer, *>?) = bindDataBuffer(0, buffer)

fun DrawCommandBuffer.bindCameraDataBuffer(buffer: Buffer<DataBuffer, *>?) = bindDataBuffer(1, buffer)

fun DrawCommandBuffer.bindObjectTransformBuffer(buffer: Buffer<DataBuffer, *>?) = bindDataBuffer(2, buffer)

fun DrawCommandBuffer.bindObjectMaterialBuffer(buffer: Buffer<DataBuffer, *>?) = bindDataBuffer(3, buffer)