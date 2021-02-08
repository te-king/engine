package extensions

import kotlin.reflect.KClass


//val factory = mutableMapOf<KClass<*>, (Array<out Any>) -> Any>(
//    Transform::class to { Transform(it.first() as Node) }
//)


//actual fun <T : Any> KClass<T>.createInstance(vararg args: Any): T {
//    TODO()
////    return factory[this]?.invoke(args).let(this::cast)
//}