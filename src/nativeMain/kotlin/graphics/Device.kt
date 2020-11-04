package graphics


actual class Device {

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <F : TextureFormat> createTexture2d(width: Long, height: Long, levels: Long, format: F): Texture<F, Texture2d>? {
        TODO("Not yet implemented")
    }

    actual fun createVertexLayout(vararg mappings: Pair<Int, VertexKind>): VertexLayout? {
        TODO("Not yet implemented")
    }

    actual fun createDrawCommandBuffer(): DrawCommandBuffer? {
        TODO("Not yet implemented")
    }

}