import ecs.Added
import ecs.Changed
import ecs.NaiveMapBackend
import ecs.system


data class Label(val v: String) {
    override fun toString() = v
}

enum class State {
    Enabled,
    Disabled
}


data class GraphicsWindow(val handle: Long)

data class GraphicsStep(val counter: Long, val delta: Double)


fun main() {

    val debugStateChange = system<Label, @Added @Changed State> { label, state ->
        println("$label:${entity.id} changed to $state")
    }

    val clearFrame = system<GraphicsWindow, @Changed GraphicsStep> { window, graphics ->
        // window set title to frame count
        // window clear with context
    }


    val b = NaiveMapBackend()
    b.spawn(Label("First"), State.Enabled)
    b.spawn(Label("Second"), State.Disabled)

    b.run(listOf(debugStateChange))
}