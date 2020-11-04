package graphics

actual class Device {

    actual fun <K : BufferKind, S : BufferStorage> createBuffer(kind: K, storage: S): Buffer<K, S> {
        TODO("Not yet implemented")
    }

    actual fun createTexture() {
        TODO("Not yet implemented")
    }

    actual fun createVertexLayout() {
        TODO("Not yet implemented")
    }

    actual fun createDrawCommandBuffer(): DrawCommandBuffer {
        TODO("Not yet implemented")
    }

}