package world

import ecs.ArchetypeBackend
import ecs.System


expect class Client {

    var currentBackend: ArchetypeBackend


    fun exit()

    fun start(systems: List<System>)

}


