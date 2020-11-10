package world

import world.components.Component
import kotlin.reflect.KClass


class Node(val scene: Scene) {

    val componentMap = mutableMapOf<KClass<out Component>, Component>()
    val components get() = componentMap.values.asSequence()

    inline fun <reified T : Component> getOrAdd(noinline ctor: (Node) -> T) = componentMap.getOrPut(T::class) { ctor(this) } as T
    inline fun <reified T : Component> get() = componentMap[T::class] as T?
    inline fun <reified T : Component> contains() = componentMap.containsKey(T::class)
    inline fun <reified T : Component> delete() = componentMap.remove(T::class) != null

}