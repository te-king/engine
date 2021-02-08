package graphics

import math.Float4


abstract class Mesh {

    abstract val vertices: Sequence<Sequence<Float4>>

    abstract val indicies: Sequence<Sequence<Int>>


    operator fun get(index: Int) = Submesh(this, index)

}


