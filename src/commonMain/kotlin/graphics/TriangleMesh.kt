package graphics

import math.Float4
import math.float4ArrayOf

object TriangleMesh : Mesh() {

    override val verticesList = listOf(
        float4ArrayOf( // positions
            Float4(-.5f, -.5f, 0f, 1f),
            Float4(0f, .5f, 0f, 1f),
            Float4(.5f, -.5f, 0f, 1f)
        )
    )

    override val indiciesList = listOf(
        intArrayOf(
            2, 1, 0
        )
    )

}

