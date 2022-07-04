package ecs

import kotlin.reflect.KClass


class NaiveMapBackend : Backend {

    private var counter = 0UL

    private val componentTable = mutableMapOf<KClass<out Any>, MutableMap<Entity, Any>>()


    override val entities get() = componentTable.flatMap { it.value.keys }.asSequence().distinct()


    override fun spawn(vararg components: Any): Entity {
        val result = Entity(counter++)
        for (component in components) componentTable.getOrPut(component::class, ::mutableMapOf)[result] = component
        return result
    }

    override fun destroy(entity: Entity): Boolean = componentTable.values.any { it.remove(entity) != null }


    override fun run(systems: List<System>) {
        for (system in systems) {
            for (entity in entities) {

                val args = system.args.map { componentTable[it]!![entity] }

                if (null in args) continue

                val s = object : SystemScope {
                    override val entity get() = entity
                }

                system(s, args.filterNotNull().toTypedArray())

            }
        }
    }

    override fun contains(component: KClass<out Any>): Boolean {
        return componentTable.contains(component)
    }

    override fun contains(entity: Entity): Boolean {
        return componentTable.any { (k, v) -> v.contains(entity) }
    }

}