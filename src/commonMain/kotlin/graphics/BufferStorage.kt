package graphics


sealed interface BufferStorage

object DynamicStorage : BufferStorage
object ClientStorage : BufferStorage
object ServerStorage : BufferStorage