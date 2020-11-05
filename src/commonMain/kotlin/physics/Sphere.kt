package physics

import math.Float3
import physics.Collider
import physics.Sdf


class Sphere(val radius: Float) : Collider, Sdf {

    override fun march(eye: Float3) = eye.length - radius

}


