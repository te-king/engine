package ecs

import kotlin.reflect.KClass


interface Backend {

    val entities: Sequence<Entity>

    fun spawn(vararg components: Any): Entity

    fun destroy(entity: Entity): Boolean

    suspend fun run(systems: List<System>)


    operator fun contains(entity: Entity): Boolean

    operator fun contains(component: KClass<out Any>): Boolean

}