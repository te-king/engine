package graphics


sealed class BufferKind
object DataKind: BufferKind()
object VertexKind: BufferKind()
object IndexKind: BufferKind()