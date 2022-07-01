package ecs

import kotlin.reflect.KClass


interface System {
    val args: Array<KClass<out Any>>
    operator fun invoke(scope: SystemScope, vararg args: Any)
}

interface SystemScope {
    val entity: Entity
}


inline fun system(crossinline fn: SystemScope.() -> Unit) =
    object : System {
        override val args = emptyArray<KClass<out Any>>()
        override fun invoke(scope: SystemScope, vararg args: Any) = scope.fn()
    }

inline fun <reified C0 : Any> system(crossinline fn: SystemScope.(C0) -> Unit) =
    object : System {
        override val args = arrayOf<KClass<out Any>>(C0::class)
        override fun invoke(scope: SystemScope, vararg args: Any) = scope.fn(args[0] as C0)
    }

inline fun <reified C0 : Any, reified C1 : Any> system(noinline fn: SystemScope.(C0, C1) -> Unit) =
    object : System {
        override val args = arrayOf<KClass<out Any>>(C0::class, C1::class)
        override fun invoke(scope: SystemScope, vararg args: Any) = scope.fn(args[0] as C0, args[1] as C1)
    }

//inline fun <reified C0 : Any, reified C1 : Any, reified C2 : Any> system(noinline fn: (Entity, C0, C1, C2) -> Unit) =
//    object : System {
//        override val args = arrayOf<KClass<out Any>>(C0::class, C1::class, C2::class)
//        override fun invoke(entity: Entity, vararg args: Any) = fn(entity, args[0] as C0, args[1] as C1, args[2] as C2)
//    }