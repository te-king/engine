package graphics

import kotlin.reflect.KClass


class VertexBufferObject(val buffer: Buffer<VertexBuffer, *>, val type: KClass<*>, val offset: Long, val stride: Int = 0)

