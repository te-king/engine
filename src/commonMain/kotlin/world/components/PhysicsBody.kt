package world.components

import math.Float3
import math.Quaternion


data class PhysicsBody(
    val positionDelta: Float3 = Float3.zero,
    val rotationDelta: Quaternion = Quaternion.identity
)