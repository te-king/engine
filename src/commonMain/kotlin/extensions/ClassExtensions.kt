package extensions

import kotlin.reflect.KClass


expect fun<T: Any> KClass<T>.createInstance(vararg args: Any): T