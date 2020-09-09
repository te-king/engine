package world.components

import world.Node
import world.controllers.GraphicsContext


class Transform(node: Node) : Component(node) {

    private val graphicsContext by controller<GraphicsContext>()

}