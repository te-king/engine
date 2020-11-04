package extensions

import world.Node
import world.Scene


val Scene.components get() = nodes.flatMap(Node::components)

inline fun <reified T> Scene.controllersOfType() = controllers.filterIsInstance<T>()

inline fun <reified T> Scene.componentsOfType() = components.filterIsInstance<T>()