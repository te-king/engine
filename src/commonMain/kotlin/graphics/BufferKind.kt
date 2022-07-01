package graphics


sealed interface BufferKind

object DataKind : BufferKind
object VertexKind : BufferKind
object IndexKind : BufferKind