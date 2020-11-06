package world.components

import extensions.slerp
import math.Float3
import math.Quaternion
import physics.Collider
import physics.Sphere
import world.Node
import world.Updatable


class Body(node: Node) : Component(node), Updatable {

    val transform by component<Transform>()


    var collider: Collider = Sphere(1f)

    var static = false


    var mass = 1f
        get() = if (static) Float.MAX_VALUE else field
        set(value) {
            field = value
            massInverse = 1f / value
        }

    var massInverse = 1f / mass
        get() = if (static) 0f else field
        private set


    var translationDelta = Float3.zero
        get() = if (static) Float3.zero else field

    var rotationDelta = Quaternion.identity
        get() = if (static) Quaternion.identity else field


    override fun update(delta: Double) {
        transform.translation += translationDelta * delta.toFloat()
        transform.rotation = slerp(transform.rotation, transform.rotation * rotationDelta, delta.toFloat())
    }

}