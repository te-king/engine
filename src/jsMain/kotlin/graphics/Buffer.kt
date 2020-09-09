package graphics

import org.khronos.webgl.WebGLBuffer


actual class Buffer<K: BufferKind>(val handle: WebGLBuffer, val kind: K)