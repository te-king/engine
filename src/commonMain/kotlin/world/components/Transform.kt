package world.components

import math.Float3
import math.Quaternion


data class Transform(
    val position: Float3 = Float3.zero,
    val rotation: Quaternion = Quaternion.identity,
    val scale: Float3 = Float3.one
)