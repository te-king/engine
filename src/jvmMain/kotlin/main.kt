import ecs.NaiveMapBackend
import ecs.system
import kotlin.time.ExperimentalTime


data class Label(val v: String)

object Enabled


@ExperimentalTime
fun main() {

    val s1 = system<Label, Enabled> { l, _ ->
        println("$l ($entity)")
    }


    val b = NaiveMapBackend()
    b.spawn(Label("Title"), Enabled)
    b.spawn(Label("Title 2"), Enabled)

    b.run(s1)
}