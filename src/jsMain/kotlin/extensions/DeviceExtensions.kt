package extensions

import graphics.Device
import graphics.DeviceState
import graphics.DrawCommandBuffer
import org.khronos.webgl.WebGL2RenderingContext.Companion.BLEND
import org.khronos.webgl.WebGL2RenderingContext.Companion.CULL_FACE
import org.khronos.webgl.WebGL2RenderingContext.Companion.DEPTH_TEST
import org.khronos.webgl.WebGL2RenderingContext.Companion.DRAW_FRAMEBUFFER
import org.khronos.webgl.WebGL2RenderingContext.Companion.READ_FRAMEBUFFER
import org.khronos.webgl.WebGL2RenderingContext.Companion.STENCIL_TEST


actual inline fun Device.draw(state: DeviceState, crossinline fn: DrawCommandBuffer.() -> Unit) {
    val commandBuffer = createDrawCommandBuffer()?.also(fn) ?: return

    context.bindFramebuffer(READ_FRAMEBUFFER, state.readFramebuffer?.handle)
    context.bindFramebuffer(DRAW_FRAMEBUFFER, state.writeFramebuffer?.handle)

    context.frontFace(state.winding.native)

    if (state.cullFunc != null) {
        context.enable(CULL_FACE)
        context.cullFace(state.cullFunc.native)
    } else {
        context.disable(CULL_FACE)
    }

    if (state.blendFunction != null) {
        context.enable(BLEND)
        // TODO
    } else {
        context.disable(BLEND)
    }

    if (state.depthFunction != null) {
        context.enable(DEPTH_TEST)
        context.depthFunc(state.depthFunction.native)
    } else {
        context.disable(DEPTH_TEST)
    }

    commandBuffer.submit()
}