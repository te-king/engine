package world.controllers

import extensions.dot
import extensions.lerp
import extensions.pairedPermutations
import extensions.slerp
import math.Float3
import math.Quaternion
import physics.Collision
import physics.collide
import world.Scene
import world.Updatable
import world.components.Body


class Physics(scene: Scene) : Controller(scene), Updatable {

    private val physicsBodies by components<Body>()

    var gravity = Float3(0f, -9.81f, 0f)


    override fun update(delta: Double) {

        for (physicsBody in physicsBodies) {
            physicsBody.translationDelta += gravity * delta.toFloat()
            physicsBody.translationDelta = lerp(physicsBody.translationDelta, Float3.zero, delta.toFloat())
            physicsBody.rotationDelta = slerp(physicsBody.rotationDelta, Quaternion.identity, delta.toFloat())
        }

        for ((first, second) in physicsBodies.pairedPermutations())
            collide(first, second).forEach(::resolveCollision)

    }


    private fun resolveCollision(collision: Collision) {

        val relativeVelocity = collision.second.translationDelta - collision.first.translationDelta
        val velocityAlongNormal = dot(relativeVelocity, collision.normal)

        // Handle intersection
        val displacementScalar = collision.seperation / (collision.first.massInverse + collision.second.massInverse)
        val displacement = collision.normal * displacementScalar
        collision.first.transform.translation += displacement * collision.first.massInverse
        collision.second.transform.translation -= displacement * collision.second.massInverse

        when {
            velocityAlongNormal < 0 -> {
                // Moving towards
                val impulseScalar = -(1 + collision.restitution) * velocityAlongNormal / (collision.first.massInverse + collision.second.massInverse)
                val impulse = collision.normal * impulseScalar
                collision.first.translationDelta -= impulse * collision.first.massInverse
                collision.second.translationDelta += impulse * collision.second.massInverse
            }
            velocityAlongNormal < 0.25 -> {
                // Moving away very slowly
                // TODO: zero acceleration in direction
            }
        }

    }

}