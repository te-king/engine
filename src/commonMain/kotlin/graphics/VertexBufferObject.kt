package graphics

import kotlin.reflect.KClass


class VertexBufferObject(val buffer: Buffer<VertexKind, *>, val type: KClass<*>, val offset: Long, val stride: Int = 0)

