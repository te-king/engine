package ecs

import kotlin.reflect.KClass


sealed interface Query<T>

class Exists<T : Any>(val type: KClass<T>) : Query<T> {
    companion object {
        inline operator fun <reified T : Any> invoke() = Exists(T::class)
    }
}

class NotExists<T : Any>(val type: KClass<T>) : Query<Nothing?> {
    companion object {
        inline operator fun <reified T : Any> invoke() = NotExists(T::class)
    }
}

class ExistsOrNull<T : Any>(val type: KClass<T>) : Query<T?> {
    companion object {
        inline operator fun <reified T : Any> invoke() = ExistsOrNull(T::class)
    }
}


//class Changed<T> : Query<T>
//class Added<T> : Query<T>
//class Removed<T> : Query<Unit>