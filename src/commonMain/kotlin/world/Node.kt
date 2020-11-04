package world

import extensions.createInstance
import world.components.Component
import kotlin.reflect.KClass


abstract class Node(val scene: Scene) {

    private val componentMap = mutableMapOf<KClass<out Component>, Component>()
    val components get() = componentMap.values.asSequence()

    fun <T : Component> add(type: KClass<T>) = componentMap.getOrPut(type) { type.createInstance(this) } as T
    fun <T : Component> get(type: KClass<T>) = componentMap[type] as T?
    fun <T : Component> contains(type: KClass<T>) = componentMap.containsKey(type)
    fun <T : Component> delete(type: KClass<T>) = componentMap.remove(type) != null

    abstract fun remove()

}