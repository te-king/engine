package graphics

import math.Float4Array


abstract class Mesh {

    abstract val verticesList: List<Float4Array>

    abstract val indiciesList: List<IntArray>


    operator fun get(index: Int) = Submesh(this, index)

}


