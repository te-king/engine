package graphics


expect class Buffer<K : BufferKind, M : BufferStorage> {

    val device: Device

    inner class Context

}


expect inline operator fun Buffer<DataKind, DynamicStorage>.invoke(context: Buffer<DataKind, DynamicStorage>.Context.() -> Unit)

expect fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: ByteArray)
expect fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: ShortArray)
expect fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: IntArray)
expect fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: LongArray)
expect fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray)
expect fun Buffer<DataKind, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray)


expect inline operator fun Buffer<VertexKind, DynamicStorage>.invoke(context: Buffer<VertexKind, DynamicStorage>.Context.() -> Unit)

expect fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: FloatArray)
expect fun Buffer<VertexKind, DynamicStorage>.Context.writeData(offset: Long, data: DoubleArray)


expect inline operator fun Buffer<IndexKind, DynamicStorage>.invoke(context: Buffer<IndexKind, DynamicStorage>.Context.() -> Unit)

expect fun Buffer<IndexKind, DynamicStorage>.Context.writeData(offset: Long, data: IntArray)