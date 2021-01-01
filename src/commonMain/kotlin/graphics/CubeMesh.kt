package graphics

import math.Float4
import math.float4ArrayOf

object CubeMesh : Mesh() {

    override val verticesList = listOf(
        float4ArrayOf( // positions
            Float4(1f, -1f, -1f, 1f),
            Float4(1f, -1f, 1f, 1f),
            Float4(-1f, -1f, 1f, 1f),
            Float4(-1f, -1f, -1f, 1f),
            Float4(1f, 1f, -1f, 1f),
            Float4(1f, 1f, 1f, 1f),
            Float4(-1f, 1f, 1f, 1f),
            Float4(-1f, 1f, -1f, 1f)
        )
    )

    // TODO:

    override val indiciesList = listOf(
        intArrayOf(
            2, 3, 4,
            8, 7, 6,
            5, 6, 2,
            6, 7, 3,
            3, 7, 8,
            1, 4, 8,
            1, 2, 4,
            5, 8, 6,
            1, 5, 2,
            2, 6, 3,
            4, 3, 8,
            5, 1, 8,
        )
    )

}