package extensions

import world.Node
import world.components.Component
import world.components.Transform


inline fun <reified T> Node.componentsOfType() = components.filterIsInstance<T>()

val Node.parent get() = get<Transform>()?.parent

val Node.children get() = get<Transform>()?.children?.map(Component::node) ?: emptySequence()