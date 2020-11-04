package graphics

actual class Buffer<K : BufferKind, M : BufferStorage>(actual val device: Device) {

    actual inner class Context

    actual inline operator fun invoke(context: Context.() -> Unit) {

    }

}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ByteArray) {
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: ShortArray) {
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: IntArray) {
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: LongArray) {
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: FloatArray) {
}

actual fun Buffer<*, DynamicBuffer>.Context.writeData(offset: Long, data: DoubleArray) {
}