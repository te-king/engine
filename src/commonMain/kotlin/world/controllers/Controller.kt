package world.controllers

import world.Scene
import world.components.Component
import kotlin.properties.ReadOnlyProperty


abstract class Controller(val scene: Scene) {

    protected inline fun <reified T : Component> component() =
        ReadOnlyProperty<Component, Sequence<T>> { _, _ -> scene.nodes.flatMap { it.components }.filterIsInstance<T>() }

    protected inline fun <reified T : Controller> controller() =
        ReadOnlyProperty<Component, T> { _, _ -> scene.add(T::class) }

}