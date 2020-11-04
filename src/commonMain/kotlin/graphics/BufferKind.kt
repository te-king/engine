package graphics


sealed class BufferKind
object DataBuffer: BufferKind()
object VertexBuffer: BufferKind()
object IndexBuffer: BufferKind()