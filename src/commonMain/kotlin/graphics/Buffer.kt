package graphics


expect class Buffer<K : BufferKind, M : BufferStorage> {

    val device: Device


    inner class Context

    inline operator fun invoke(context: Context.() -> Unit)

}

expect fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: ByteArray)
expect fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: ShortArray)
expect fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: IntArray)
expect fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: LongArray)
expect fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray)
expect fun Buffer<*, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray)