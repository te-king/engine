package world.controllers

import extensions.componentsOfType
import world.Scene
import world.components.Component
import kotlin.properties.ReadOnlyProperty


abstract class Controller(val scene: Scene) {

    protected inline fun <reified T> components() =
        ReadOnlyProperty<Controller, Sequence<T>> { _, _ -> scene.componentsOfType<T>() }

    protected inline fun <reified T : Controller> controller(noinline ctor: (Scene) -> T) =
        ReadOnlyProperty<Controller, T> { _, _ -> scene.getOrAdd(ctor) }

}