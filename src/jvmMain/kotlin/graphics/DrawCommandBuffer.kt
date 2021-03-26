package graphics

import kotlinx.coroutines.runBlocking
import math.Color
import org.lwjgl.opengl.GL45C.*


actual class DrawCommandBuffer(actual val device: Device, val commands: MutableList<() -> Unit>) {

    actual fun bindDataBuffer(index: Int, buffer: Buffer<DataKind, *>?) {
        commands.add {
            glBindBufferBase(GL_UNIFORM_BUFFER, index, buffer?.id ?: 0)
        }
    }

    actual fun bindVertexBuffer(index: Int, buffer: VertexBufferObject?) {
        if (buffer != null)
            commands.add {
                glEnableVertexAttribArray(index)
                glBindVertexBuffer(index, buffer.buffer.id, buffer.offset, buffer.stride)
            }
        else
            commands.add {
                glDisableVertexAttribArray(index)
            }
    }

    actual fun bindIndexBuffer(buffer: Buffer<IndexKind, *>?) {
        if (buffer == null)
            commands.add {
                glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer?.id ?: 0)
            }
    }

    actual fun bindPipeline(pipeline: Pipeline?) {
        commands.add {
            glBindProgramPipeline(pipeline?.id ?: 0)
        }
    }


    actual fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) = Unit

    actual fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int) = Unit


    actual fun clearFramebuffer(color: Color) {
        commands.add {
            glClearColor(color.r, color.g, color.b, color.a)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT or GL_STENCIL_BUFFER_BIT)
        }
    }

    actual fun submit() =
        runBlocking(device.context) {
            for (command in commands) command()
        }

}