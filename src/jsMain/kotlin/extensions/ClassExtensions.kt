package extensions

import kotlin.reflect.KClass


actual fun <T : Any> KClass<T>.createInstance(vararg args: Any): T {
    val constructor = this
    return js("new constructor(args)") as T
}