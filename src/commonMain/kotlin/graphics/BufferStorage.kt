package graphics


sealed class BufferStorage
object DynamicBuffer : BufferStorage()
object ClientBuffer : BufferStorage()
object ServerBuffer : BufferStorage()