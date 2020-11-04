import world.Scene
import world.components.Transform


fun main() {

    val scene = Scene()

    val node = scene.addNode {
        val transform = add(Transform::class)
    }

}