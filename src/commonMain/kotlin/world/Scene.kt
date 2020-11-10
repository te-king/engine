package world

import world.controllers.Controller
import kotlin.reflect.KClass


class Scene {

    val controllerMap = mutableMapOf<KClass<out Controller>, Controller>()
    val controllers get() = controllerMap.values.asSequence()

    inline fun <reified T : Controller> getOrAdd(noinline ctor: (Scene) -> T) = controllerMap.getOrPut(T::class) { ctor(this) } as T
    inline fun <reified T : Controller> get() = controllerMap[T::class] as T?
    inline fun <reified T : Controller> contains() = controllerMap.containsKey(T::class)
    inline fun <reified T : Controller> delete() = controllerMap.remove(T::class) != null


    val nodeSet = mutableSetOf<Node>()
    val nodes get() = nodeSet.asSequence()
    fun node() = Node(this).also(nodeSet::add)

}