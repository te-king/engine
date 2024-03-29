package graphics


actual class Device {

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: ShortArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: IntArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: LongArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: FloatArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(data: DoubleArray, kind: K, storage: S): Buffer<K, S>? {
        TODO("Not yet implemented")
    }

    actual fun <F : TextureFormat> createTexture2d(width: Long, height: Long, levels: Long, format: F): Texture<F, Texture2d>? {
        TODO("Not yet implemented")
    }

    actual fun <K : ShaderKind> createShader(source: String, kind: K): Shader<K>? {
        TODO("Not yet implemented")
    }

    actual fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>, vararg mappings: Pair<String, Int>): Pipeline? {
        TODO("Not yet implemented")
    }

    actual fun createDrawCommandBuffer(deviceState: DeviceState): DrawCommandBuffer? {
        TODO("Not yet implemented")
    }

}