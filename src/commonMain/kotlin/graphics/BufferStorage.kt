package graphics


sealed class BufferStorage
object DynamicStorage : BufferStorage()
object ClientStorage : BufferStorage()
object ServerStorage : BufferStorage()