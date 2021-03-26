package world.components

import extensions.slerp
import math.Float2
import math.Float3
import math.Quaternion
import physics.Collider
import physics.Sphere
import world.Node
import world.Updatable


class PhysicsBody(node: Node) : Component(node), Updatable {

    private val transform by component(::Transform)

    private val contactHandlers by components<ContactHandler>()



    var collider: Collider = Sphere(1f)

    var groups = setOf("default")

    var static = false


    var mass = 1f
        get() = if (static) Float.POSITIVE_INFINITY else field
        set(value) {
            field = value
            massInverse = 1f / value
        }

    var massInverse = 1f / mass
        get() = if (static) 0f else field
        private set


    /**
     * The change in translation every second
     */
    var translationDelta = Float3.zero
        get() = if (static) Float3.zero else field

    /**
     * The change in rotation every second
     */
    var rotationDelta = Quaternion.identity
        get() = if (static) Quaternion.identity else field


    val kineticEnergy
        get() = 0.5 * mass * translationDelta.lengthSquared


    override fun update(delta: Double) {
        transform.translation += translationDelta * delta.toFloat()
        transform.rotation = slerp(transform.rotation, transform.rotation * rotationDelta, delta.toFloat())
    }


    interface ContactHandler {
        fun contact(other: PhysicsBody, constant: Boolean)
    }

}