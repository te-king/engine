package world.components

import extensions.componentsOfType
import world.Node
import world.controllers.Controller
import kotlin.properties.ReadOnlyProperty


abstract class Component(val node: Node) {

    val scene get() = node.scene

    protected inline fun <reified T : Component> component() =
        ReadOnlyProperty<Component, T> { _, _ -> node.add(T::class) }

    protected inline fun <reified T> components() =
        ReadOnlyProperty<Component, Sequence<T>> { _, _ -> node.componentsOfType<T>() }

    protected inline fun <reified T : Controller> controller() =
        ReadOnlyProperty<Component, T> { _, _ -> node.scene.add(T::class) }

}