import ecs.NaiveMapBackend
import ecs.system
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KClass


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

    var i = 0

    val tester = system<State> { _, state ->
        when (state) {
            State.Enabled -> send(Label("Enabled"))
            State.Disabled -> send(Label("Disabled"))
        }
        i++
    }

    val b = NaiveMapBackend()
    b.spawn(Label("First"), State.Enabled)
    b.spawn(Label("Second"), State.Enabled)
    b.spawn(Label("Second"))

    runBlocking {
        b.run(listOf(tester, tester))
    }
}