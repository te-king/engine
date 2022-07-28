package ecs

import extensions.mapArray
import kotlin.reflect.KClass


abstract class System(queries: Array<Query<*>>) {

    val exists = queries.filterIsInstance<Exists<*>>().map(Exists<*>::type)
    val notExists = queries.filterIsInstance<NotExists<*>>().map(NotExists<*>::type)
    val existsOrNull = queries.filterIsInstance<ExistsOrNull<*>>().map(ExistsOrNull<*>::type)

    val args = queries
        .mapArray {
            when (it) {
                is Exists<*> -> it.type
                is NotExists<*> -> it.type
                is ExistsOrNull<*> -> it.type
            }
        }

    abstract operator fun invoke(scope: SystemScope, args: List<*>)

}


interface SystemScope {
    fun <T : Any> addOrUpdate(type: KClass<T>, value: T)
    fun <T : Any> remove(type: KClass<T>)

    fun spawnEntity(vararg components: Any)
    fun despawnEntity()
}


inline fun <reified T : Any> SystemScope.addOrUpdate(value: T) = addOrUpdate(T::class, value)

inline fun <reified T : Any> SystemScope.remove() = remove(T::class)


fun <C0> system(
    c0: Query<C0>,
    fn: SystemScope.(C0) -> Unit
) = object : System(arrayOf(c0)) {

    override fun invoke(scope: SystemScope, args: List<*>) = fn(scope, args[0] as C0)

}

fun <C0, C1> system(
    c0: Query<C0>,
    c1: Query<C1>,
    fn: SystemScope.(C0, C1) -> Unit
) = object : System(arrayOf(c0, c1)) {

    override fun invoke(scope: SystemScope, args: List<*>) = fn(scope, args[0] as C0, args[1] as C1)

}

fun <C0, C1, C2> system(
    c0: Query<C0>,
    c1: Query<C1>,
    c2: Query<C2>,
    fn: SystemScope.(C0, C1, C2) -> Unit
) = object : System(arrayOf(c0, c1, c2)) {

    override fun invoke(scope: SystemScope, args: List<*>) = fn(scope, args[0] as C0, args[1] as C1, args[2] as C2)

}