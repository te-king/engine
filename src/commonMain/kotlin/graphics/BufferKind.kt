package graphics


sealed class BufferKind
object GeneralBuffer: BufferKind()
object VertexBuffer: BufferKind()
object IndexBuffer: BufferKind()