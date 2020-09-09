package world

import extensions.createInstance
import world.controllers.Controller
import world.controllers.GraphicsContext
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


class Scene {

    private val controllerMap = mutableMapOf<KClass<out Controller>, Controller>()
    val controllers get() = controllerMap.values.asSequence()

    fun <T : Controller> add(type: KClass<T>) = controllerMap.getOrPut(type) { type.createInstance(this) } as T
    fun <T : Controller> get(type: KClass<T>) = controllerMap[type] as T?
    fun <T : Controller> contains(type: KClass<T>) = controllerMap.containsKey(type)
    fun <T : Controller> delete(type: KClass<T>) = controllerMap.remove(type) != null


    private val nodeSet = mutableSetOf<Node>()
    val nodes get() = nodeSet.asSequence()

    fun addNode(fn: Node.() -> Unit = {}) =
        object : Node(this) {

            init {
                nodeSet.add(this)
            }

            init {
                fn()
            }

        }

    fun deleteNode(node: Node) =
        nodeSet.remove(node)

}