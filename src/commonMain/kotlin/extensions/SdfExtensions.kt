package extensions

import physics.Intersection
import physics.Sdf
import physics.Subtraction
import physics.Union


operator fun Sdf.minus(other: Sdf) = Subtraction(this, other)

infix fun Sdf.and(other: Sdf) = Intersection(this, other)

infix fun Sdf.or(other: Sdf) = Union(this, other)
