package ecs

import java.util.concurrent.atomic.AtomicLong
import kotlin.reflect.KClass


class NaiveMapBackend : Backend {

    private val counter = AtomicLong(0)

    private val componentTable = mutableMapOf<KClass<out Any>, MutableMap<ULong, Any>>()

    override fun spawn(vararg components: Any): Entity {
        val id = counter.incrementAndGet().toULong()
        for (component in components) componentTable.getOrPut(component::class, ::mutableMapOf)[id] = component
        return Entity(id)
    }

    override fun remove(entity: Entity): Boolean = componentTable.values.any { it.remove(entity.id) != null }

    override fun entities(): Sequence<ULong> = componentTable.flatMap { it.value.keys }.asSequence().distinct()


    override fun <C : Any> has(id: ULong, key: KClass<C>): Boolean {
        return id in componentTable[key]!!
    }

    override fun <C : Any> get(id: ULong, key: KClass<C>): C? {
        return componentTable[key]!![id] as C?
    }
}