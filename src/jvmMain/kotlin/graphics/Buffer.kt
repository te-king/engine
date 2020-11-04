package graphics


actual class Buffer<K: BufferKind, S: BufferStorage>


actual fun Buffer<*, DynamicBuffer>.writeData(offset: Long, data: ByteArray) {
    TODO()
}

actual fun Buffer<*, DynamicBuffer>.writeData(offset: Long, data: ShortArray) {
    TODO()
}

actual fun Buffer<*, DynamicBuffer>.writeData(offset: Long, data: IntArray) {
    TODO()
}

actual fun Buffer<*, DynamicBuffer>.writeData(offset: Long, data: LongArray) {
    TODO()
}

actual fun Buffer<*, DynamicBuffer>.writeData(offset: Long, data: FloatArray) {
    TODO()
}

actual fun Buffer<*, DynamicBuffer>.writeData(offset: Long, data: DoubleArray) {
    TODO()
}