package extensions

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor


actual fun <T : Any> KClass<T>.createInstance(vararg args: Any): T {
    return this.primaryConstructor?.call(args) ?: error("Missing primary constructor for type $this")
}