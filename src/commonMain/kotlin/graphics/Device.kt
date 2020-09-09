package graphics


expect class Device {

    fun createBuffer(): Buffer

    fun createTexture()

    fun createVertexLayout()


    fun createDrawCommandBuffer(): DrawCommandBuffer

}


inline fun Device.draw(crossinline fn: DrawCommandBuffer.() -> Unit) = createDrawCommandBuffer().also(fn).submit()