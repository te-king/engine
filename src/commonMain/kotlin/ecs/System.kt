package ecs

import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlin.reflect.KClass
import kotlin.reflect.typeOf


interface System {
    val args: Array<KClass<*>>
    val optional: BooleanArray
    operator fun invoke(entity: Entity, args: Array<*>): Flow<Any>
}


inline fun system(noinline fn: suspend ProducerScope<Any>.(Entity) -> Unit) =
    object : System {
        override val args = emptyArray<KClass<*>>()
        override val optional = booleanArrayOf()
        override fun invoke(entity: Entity, args: Array<*>) = channelFlow { fn(entity) }
    }

inline fun <reified C0> system(noinline fn: suspend ProducerScope<Any>.(Entity, C0) -> Unit) =
    object : System {
        override val args = arrayOf<KClass<*>>(C0::class)
        override val optional = booleanArrayOf(typeOf<C0>().isMarkedNullable)
        override fun invoke(entity: Entity, args: Array<*>) = channelFlow { fn(entity, args[0] as C0) }
    }

inline fun <reified C0, reified C1> system(noinline fn: suspend ProducerScope<Any>.(Entity, C0, C1) -> Unit) =
    object : System {
        override val args = arrayOf<KClass<*>>(C0::class, C1::class)
        override val optional = booleanArrayOf(typeOf<C0>().isMarkedNullable, typeOf<C1>().isMarkedNullable)
        override fun invoke(entity: Entity, args: Array<*>) = channelFlow { fn(entity, args[0] as C0, args[1] as C1) }
    }