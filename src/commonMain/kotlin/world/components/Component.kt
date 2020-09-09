package world.components

import world.Node
import world.controllers.Controller
import kotlin.properties.ReadOnlyProperty


abstract class Component(val node: Node) {

    protected inline fun <reified T : Component> component() =
        ReadOnlyProperty<Component, T> { _, _ -> node.add(T::class) }

    protected inline fun <reified T : Controller> controller() =
        ReadOnlyProperty<Component, T> { _, _ -> node.scene.add(T::class) }

}