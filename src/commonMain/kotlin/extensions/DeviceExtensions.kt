package extensions

import graphics.Device
import graphics.DrawCommandBuffer
import graphics.TextureFormat


inline fun Device.draw(crossinline fn: DrawCommandBuffer.() -> Unit) = createDrawCommandBuffer()?.also(fn)?.submit()


fun <F : TextureFormat> Device.createImage2d(width: Long, height: Long, format: F) = createTexture2d(width, height, 1, format)