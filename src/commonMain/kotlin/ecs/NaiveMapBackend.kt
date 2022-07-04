package ecs

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlin.reflect.KClass


class NaiveMapBackend : Backend {

    private var counter = 0UL

    private val table = mutableMapOf<KClass<out Any>, MutableMap<Entity, Any>>()


    override val entities get() = table.flatMap { it.value.keys }.asSequence().distinct()


    override fun spawn(vararg components: Any): Entity {
        val result = Entity(counter++)
        for (component in components) table.getOrPut(component::class, ::mutableMapOf)[result] = component
        return result
    }

    override fun destroy(entity: Entity): Boolean = table.values.any { it.remove(entity) != null }


    override suspend fun run(systems: List<System>) {

//        for (system in systems) {
//
//            val columns = system
//                .args
//                .zip(system.optional.asIterable())
//                .map { table[it.first] to it.second }
//                .let {
//                }
//
//        }

        for (system in systems) {
            e@ for (entity in entities) {

                val args = mutableListOf<Any?>()

                for ((k, n) in system.args.zip(system.optional.asIterable()))
                    args += when (n) {
                        true -> table[k]?.get(entity)
                        false -> table[k]?.get(entity) ?: continue@e
                    }

                system(entity, args.toTypedArray())
                    .distinctUntilChangedBy { it::class }
                    .buffer(1, BufferOverflow.DROP_OLDEST)
                    .conflate()
                    .collect {
                        println("$it changed for $entity")
                    }

            }
        }
    }

    override fun contains(component: KClass<out Any>): Boolean {
        return table.contains(component)
    }

    override fun contains(entity: Entity): Boolean {
        return table.any { (k, v) -> v.contains(entity) }
    }

}