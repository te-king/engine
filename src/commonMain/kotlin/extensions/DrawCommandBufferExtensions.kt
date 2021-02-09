package extensions

import graphics.Buffer
import graphics.DataKind
import graphics.DrawCommandBuffer


fun DrawCommandBuffer.bindCameraTransformBuffer(buffer: Buffer<DataKind, *>?) = bindDataBuffer(0, buffer)

fun DrawCommandBuffer.bindCameraDataBuffer(buffer: Buffer<DataKind, *>?) = bindDataBuffer(1, buffer)

fun DrawCommandBuffer.bindObjectTransformBuffer(buffer: Buffer<DataKind, *>?) = bindDataBuffer(2, buffer)

fun DrawCommandBuffer.bindObjectMaterialBuffer(buffer: Buffer<DataKind, *>?) = bindDataBuffer(3, buffer)