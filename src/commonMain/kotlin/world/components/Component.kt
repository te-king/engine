package world.components

import extensions.componentsOfType
import world.Node
import world.Scene
import world.controllers.Controller
import kotlin.properties.ReadOnlyProperty


/**
 * A component represents a piece of logic that can be attached to a scene node.
 * A node can only ever hold a single instance of any given component.
 *
 * @property scene a reference the top level scene this component is contained within
 */
abstract class Component(val node: Node) {

    val scene get() = node.scene

    protected inline fun <reified T : Component> component(noinline ctor: (Node) -> T) =
        ReadOnlyProperty<Component, T> { _, _ -> node.getOrAdd(T::class, ctor) }

    protected inline fun <reified T> components() =
        ReadOnlyProperty<Component, Sequence<T>> { _, _ -> node.componentsOfType<T>() }

    protected inline fun <reified T : Controller> controller(noinline ctor: (Scene) -> T) =
        ReadOnlyProperty<Component, T> { _, _ -> node.scene.getOrAdd(T::class, ctor) }

}