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
import world.components.PhysicsBody


class PhysicsContext(scene: Scene) : Controller(scene), Updatable {

    private val physicsBodies by components<PhysicsBody>()

    var gravity = Float3(0f, -9.81f, 0f)


    override fun update(delta: Double) {

        for (physicsBody in physicsBodies) {
            physicsBody.translationDelta += gravity * delta.toFloat()
            physicsBody.translationDelta = lerp(physicsBody.translationDelta, Float3.zero, delta.toFloat())
            physicsBody.rotationDelta = slerp(physicsBody.rotationDelta, Quaternion.identity, delta.toFloat())
        }

        for ((first, second) in physicsBodies.pairedPermutations().filter { (first, second) -> first.groups.any(second.groups::contains) })
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
            velocityAlongNormal < -0.5 -> {
                // Moving towards - reflect
                val impulseScalar = -(1 + collision.restitution) * velocityAlongNormal / (collision.first.massInverse + collision.second.massInverse)
                val impulse = collision.normal * impulseScalar
                collision.first.translationDelta -= impulse * collision.first.massInverse
                collision.second.translationDelta += impulse * collision.second.massInverse

                for (handler in collision.first.contactHandlers) handler.contact(collision.second, false)
                for (handler in collision.second.contactHandlers) handler.contact(collision.first, false)
            }
            velocityAlongNormal < 0 -> {
                // Moving away very slowly - move into contact
                collision.first.translationDelta -= collision.normal * dot(collision.first.translationDelta, collision.normal)
                collision.second.translationDelta -= collision.normal * dot(collision.second.translationDelta, collision.normal)

                for (handler in collision.first.contactHandlers) handler.contact(collision.second, true)
                for (handler in collision.second.contactHandlers) handler.contact(collision.first, true)
            }
        }

    }


    fun raycast(position: Float3, direction: Float3) =
        sequence<RaycastHit> {

        }


    class RaycastHit


//    fun test(): Boolean {
//        val Q = SimpleVector(sphereOrigin)
//        Q.sub(rayOrigin)
//
//        val c: Float = Q.length()
//        val v: Float = Q.calcDot(rayDirection)
//        val d: Float = sphereRadius * sphereRadius - (c * c - v * v)
//
//        return d >= 0.0
//    }

}