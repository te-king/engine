package ecs

import kotlin.reflect.KClass


interface System {
    val args: Array<KClass<out Any>>
    operator fun invoke(scope: SystemScope, args: Array<Any>)
}

interface SystemScope {
    val entity: Entity
}


@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Added

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Changed


inline fun system(crossinline fn: SystemScope.() -> Unit) =
    object : System {
        override val args = emptyArray<KClass<out Any>>()
        override fun invoke(scope: SystemScope, args: Array<Any>) = scope.fn()
    }

inline fun <reified C0 : Any> system(crossinline fn: SystemScope.(C0) -> Unit) =
    object : System {
        override val args = arrayOf<KClass<out Any>>(C0::class)
        override fun invoke(scope: SystemScope, args: Array<Any>) = scope.fn(args[0] as C0)
    }

inline fun <reified C0 : Any, reified C1 : Any> system(noinline fn: SystemScope.(C0, C1) -> Unit) =
    object : System {
        override val args = arrayOf<KClass<out Any>>(C0::class, C1::class)
        override fun invoke(scope: SystemScope, args: Array<Any>) = scope.fn(args[0] as C0, args[1] as C1)
    }