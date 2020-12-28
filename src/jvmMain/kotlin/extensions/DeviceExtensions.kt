package extensions

import graphics.Device
import graphics.DeviceState
import graphics.DrawCommandBuffer
import org.lwjgl.opengl.GL46C.*


actual inline fun Device.draw(state: DeviceState, crossinline fn: DrawCommandBuffer.() -> Unit) {
    val commandBuffer = createDrawCommandBuffer()?.also(fn) ?: return

    glBindFramebuffer(GL_READ_FRAMEBUFFER, state.readFramebuffer?.id ?: 0)
    glBindFramebuffer(GL_DRAW_FRAMEBUFFER, state.writeFramebuffer?.id ?: 0)

    glFrontFace(state.winding.native)

//
//    if (state.cullFunc != null) {
//        context.enable(CULL_FACE)
//        context.cullFace(state.cullFunc.native)
//    } else {
//        context.disable(CULL_FACE)
//    }
//
//    if (state.blendFunction != null) {
//        context.enable(BLEND)
//        // TODO
//    } else {
//        context.disable(BLEND)
//    }
//
//    if (state.depthFunction != null) {
//        context.enable(DEPTH_TEST)
//        context.depthFunc(state.depthFunction.native)
//    } else {
//        context.disable(DEPTH_TEST)
//    }

    commandBuffer.submit()
}