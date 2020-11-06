package world.components

import math.Color
import world.Node
import world.controllers.StandardShader
import kotlin.random.Random


val rand = Random(1234)


class ContactEffect(node: Node) : Component(node), Body.ContactHandler {

    private val drawable by component<Drawable>()


    override fun contact(other: Body, constant: Boolean) {

        if (!constant) return

        println("Hello World!")

        for (mat in drawable.pairs.mapNotNull { it.second }.filterIsInstance<StandardShader.Material>()) {
            mat.diffuseColor = Color(rand.nextFloat().rem(1f), rand.nextFloat().rem(1f), rand.nextFloat().rem(1f), rand.nextFloat().rem(1f))
        }

    }

}