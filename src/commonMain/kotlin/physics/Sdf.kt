package physics

import math.Float3


interface Sdf {

    fun march(eye: Float3): Float

}