package world.components

import math.Color
import world.Node
import world.controllers.StandardShader
import kotlin.random.Random


class ContactEffect(node: Node) : Component(node), PhysicsBody.ContactHandler {

    private val drawable by component(::Drawable)


    val rand = Random(1234)

    override fun contact(other: PhysicsBody, constant: Boolean) {

        if (!constant) return

        for (mat in drawable.pairs.mapNotNull { it.second }.filterIsInstance<StandardShader.Material>()) {
            mat.diffuseColor = Color(rand.nextFloat().rem(1f), rand.nextFloat().rem(1f), rand.nextFloat().rem(1f), rand.nextFloat().rem(1f))
        }

    }

}