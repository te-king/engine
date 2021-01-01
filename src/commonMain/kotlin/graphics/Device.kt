package graphics


expect class Device {

    fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>?

    fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>?

    fun <K : BufferKind, S : BufferStorage> createBuffer(data: ShortArray, kind: K, storage: S): Buffer<K, S>?

    fun <K : BufferKind, S : BufferStorage> createBuffer(data: IntArray, kind: K, storage: S): Buffer<K, S>?

    fun <K : BufferKind, S : BufferStorage> createBuffer(data: LongArray, kind: K, storage: S): Buffer<K, S>?

    fun <K : BufferKind, S : BufferStorage> createBuffer(data: FloatArray, kind: K, storage: S): Buffer<K, S>?
    
    fun <K : BufferKind, S : BufferStorage> createBuffer(data: DoubleArray, kind: K, storage: S): Buffer<K, S>?


    fun <F : TextureFormat> createTexture2d(width: Long, height: Long, levels: Long, format: F): Texture<F, Texture2d>?


    fun <K : ShaderKind> createShader(source: String, kind: K): Shader<K>?

    fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>): Pipeline?

    fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>, vararg mappings: Pair<String, Int>): Pipeline?


    fun createDrawCommandBuffer(): DrawCommandBuffer?

}

expect inline fun Device.draw(state: DeviceState, crossinline fn: DrawCommandBuffer.() -> Unit)