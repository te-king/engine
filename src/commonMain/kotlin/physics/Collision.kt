package physics

import math.Float3
import world.components.Body
import world.components.Transform
import kotlin.math.sqrt


/**
 * intersection:
 * the distance the first element has fowled with the second item
 */
data class Collision(val first: Body, val second: Body, val normal: Float3, val seperation: Float) {

    val restitution = 0.5f

}


fun collideSphereSphere(first: Body, second: Body) =
    sequence {
        val firstCollider = first.collider as Sphere
        val secondCollider = second.collider as Sphere

        val sum = firstCollider.radius + secondCollider.radius
        val delta = second.transform.translation - first.transform.translation

        val sepSquared = delta.lengthSquared

        if (sepSquared < (sum * sum)) {
            val sep = sqrt(sepSquared)
            yield(Collision(first, second, delta / sep, sep - sum))
        }
    }


fun collideSphereContainer(first: Body, second: Body) =
    sequence {

        first.collider as Sphere
        second.collider as Container

        val delta = first.transform.translation - second.transform.translation

        if (delta.x < -.5)
            yield(Collision(first, second, Float3.left, delta.x + .5f))

        if (delta.x > .5)
            yield(Collision(first, second, Float3.right, -delta.x + .5f))

        if (delta.y < 0)
            yield(Collision(first, second, Float3.down, delta.y))

        if (delta.z < -.5)
            yield(Collision(first, second, Float3.forwards, delta.z + .5f))

        if (delta.z > .5)
            yield(Collision(first, second, Float3.backwards, -delta.z + .5f))

    }


fun collide(first: Body, second: Body) =
    when {
        first.collider is Sphere && second.collider is Sphere -> collideSphereSphere(first, second)
        first.collider is Sphere && second.collider is Container -> collideSphereContainer(first, second)
        first.collider is Container && second.collider is Sphere -> collideSphereContainer(second, first)
        else -> emptySequence()
    }