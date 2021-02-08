package world

import world.controllers.Controller
import kotlin.reflect.KClass


class Scene {

    private val controllerMap = mutableMapOf<KClass<out Controller>, Controller>()
    val controllers get() = controllerMap.values.asSequence()

    fun <T : Controller> getOrAdd(type: KClass<T>, ctor: (Scene) -> T) = controllerMap.getOrPut(type) { ctor(this) } as T
    fun <T : Controller> get(type: KClass<T>) = controllerMap[type] as T?
    fun <T : Controller> contains(type: KClass<T>) = controllerMap.containsKey(type)
    fun <T : Controller> delete(type: KClass<T>) = controllerMap.remove(type) != null


    private val nodeSet = mutableSetOf<Node>()
    val nodes get() = nodeSet.asSequence()

    @Suppress("FunctionName")
    fun Node(builder: Node.() -> Unit) = Node(this).also(nodeSet::add).let(builder)

}