package graphics


expect class Device {

    fun createBuffer()

    fun createTexture()

    fun createVertexLayout()


    fun createDrawCommandBuffer(): DrawCommandBuffer

}


inline fun Device.draw(fn: DrawCommandBuffer.() -> Unit) = createDrawCommandBuffer().also(fn).submit()