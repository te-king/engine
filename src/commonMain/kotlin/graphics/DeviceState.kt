package graphics


data class DeviceState(
    val readFramebuffer: Framebuffer? = null,
    val writeFramebuffer: Framebuffer? = null,

    val winding: FaceWinding = FaceWinding.CounterClockWise,
    val cullFunc: CullMode? = CullMode.Back,
    val blendFunction: BlendFunc? = BlendFunc.Zero,
    val depthFunction: DepthFunc? = DepthFunc.Less,

//    val stencilFunc: Unit? = null,
//    val stencilMask: UInt = 0xFFFFFFFFU,
//    val stencilOp: Unit = Unit
)

