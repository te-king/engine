package graphics

import math.Float4
import math.Float4Array
import math.float4ArrayOf


object TriangleMesh : Mesh() {

    override val vertices =
        sequenceOf(
            sequenceOf(
                Float4(-.5f, -.5f, 0f, 1f),
                Float4(0f, .5f, 0f, 1f),
                Float4(.5f, -.5f, 0f, 1f)
            )
        )

    override val indicies =
        sequenceOf(
            sequenceOf(
                2, 1, 0
            )
        )


}

