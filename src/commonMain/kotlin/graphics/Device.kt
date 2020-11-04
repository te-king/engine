package graphics


expect class Device {

    fun <K : BufferKind, S : BufferStorage> createBuffer(size: Long, kind: K, storage: S): Buffer<K, S>?

    fun <K : BufferKind, S : BufferStorage> createBuffer(data: ByteArray, kind: K, storage: S): Buffer<K, S>?


    fun <F : TextureFormat> createTexture2d(width: Long, height: Long, levels: Long, format: F): Texture<F, Texture2d>?


    fun <K : ShaderKind> createShader(source: String, kind: K): Shader<K>?

    fun createPipeline(vertexShader: Shader<VertexShader>, fragmentShader: Shader<FragmentShader>): Pipeline?


    fun createDrawCommandBuffer(): DrawCommandBuffer?

}