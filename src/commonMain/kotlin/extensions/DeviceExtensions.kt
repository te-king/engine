package extensions

import graphics.Device
import graphics.DeviceState
import graphics.DrawCommandBuffer
import graphics.TextureFormat


expect inline fun Device.draw(state: DeviceState, crossinline fn: DrawCommandBuffer.() -> Unit)

fun <F : TextureFormat> Device.createImage2d(width: Long, height: Long, format: F) = createTexture2d(width, height, 1, format)