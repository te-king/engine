package ecs

import kotlin.reflect.KClass


interface Backend {
    fun spawn(vararg components: Any): Entity
    fun remove(entity: Entity): Boolean
    fun entities(): Sequence<ULong>

    fun <C : Any> has(id: ULong, key: KClass<C>): Boolean
    fun <C : Any> get(id: ULong, key: KClass<C>): C?

    fun run(vararg systems: System) {
        for (system in systems) {
            for (entity in entities()) {

                val args = system.args.map { get(entity, it) }

                if (null in args) continue

                val s = object : SystemScope {
                    override val entity = Entity(entity)
                }

                system(s, *args.filterNotNull().toTypedArray())

            }
        }
    }
}