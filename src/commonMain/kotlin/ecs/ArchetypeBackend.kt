package ecs

import extensions.mapArray
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.reflect.KClass


class ArchetypeBackend {

    @JvmInline
    value class Archetype(val components: List<KClass<out Any>>)

    @JvmInline
    value class Container(val components: List<Any>)


    sealed class SystemToken(val sender: Container)
    class AddOrUpdateToken<T : Any>(sender: Container, val type: KClass<out T>, val value: T) : SystemToken(sender)
    class RemovalToken<T : Any>(sender: Container, val type: KClass<out T>) : SystemToken(sender)
    class SpawnToken(sender: Container, val components: Array<out Any>) : SystemToken(sender)
    class DespawnToken(sender: Container) : SystemToken(sender)


    private class SystemScopeImpl(val container: Container, val list: MutableList<SystemToken>) : SystemScope {
        override fun <T : Any> addOrUpdate(type: KClass<T>, value: T) {
            list += AddOrUpdateToken(container, type, value)
        }

        override fun <T : Any> remove(type: KClass<T>) {
            list += RemovalToken(container, type)
        }

        override fun spawnEntity(vararg components: Any) {
            list += SpawnToken(container, components)
        }

        override fun despawnEntity() {
            list += DespawnToken(container)
        }
    }


    val resources = mutableMapOf<KClass<out Any>, Any>()

    private val table = mutableMapOf<Archetype, MutableList<Container>>()


    fun spawn(vararg components: Any) {
        val archetype = components.map { it::class }.let(::Archetype)
        val container = components.toList().let(::Container)
        table.getOrPut(archetype, ::mutableListOf).add(container)
    }


    suspend fun run(systems: List<System>) = coroutineScope {

        // Run Systems
        systems
            .map { system ->
                // Run Systems
                table
                    .filterKeys { cont -> (system.exists - resources.keys - cont.components).isEmpty() }
                    .filterKeys { cont -> system.notExists.none { it in cont.components } }
                    .flatMap { (archetype, containers) ->
                        val lookup = system.args.mapArray { it to archetype.components.indexOf(it) }

                        containers.map { container ->
                            async {
                                buildList {
                                    system(SystemScopeImpl(container, this), lookup.map { container.components.getOrNull(it.second) ?: resources[it.first] })
                                }
                            }
                        }
                    }
            }
            .flatten()
            .awaitAll()
            .flatten()
            .groupBy { it.sender }
            .forEach { (cont, tokens) ->
                // Update Database

                val archetype = cont.components.map { it::class }.let(::Archetype)
                val components = cont.components.toMutableList()

                table[archetype]?.remove(cont)

                for (token in tokens)
                    when (token) {
                        is AddOrUpdateToken<*> -> {
                            components.removeIf { it::class == token.type }
                            components += token.value
                        }

                        is RemovalToken<*> -> {
                            components.removeIf { it::class == token.type }
                        }

                        is SpawnToken -> {
                            spawn(*token.components)
                        }

                        is DespawnToken -> {
                            return@forEach
                        }
                    }

                if (components.isNotEmpty())
                    spawn(*components.toTypedArray())

            }

    }

}