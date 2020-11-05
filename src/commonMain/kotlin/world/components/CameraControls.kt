package world.components

import math.Float3
import math.Quaternion
import world.Node
import world.Updatable
import world.controllers.Input


class CameraControls(node: Node) : Component(node), Updatable {

    private val input by controller<Input>()
    private val transform by component<Transform>()


    override fun update(delta: Double) {

        if (input.keyboardState["w"] == true)
            transform.translation += transform.forwards * delta.toFloat() * 2f

        if (input.keyboardState["a"] == true)
            transform.translation += transform.left * delta.toFloat() * 2f

        if (input.keyboardState["s"] == true)
            transform.translation += transform.backwards * delta.toFloat() * 2f

        if (input.keyboardState["d"] == true)
            transform.translation += transform.right * delta.toFloat() * 2f

        if (input.keyboardState[" "] == true)
            transform.translation += transform.up * delta.toFloat() * 2f

        if (input.keyboardState["Control"] == true)
            transform.translation += transform.down * delta.toFloat() * 2f

        if (input.keyboardState["ArrowLeft"] == true)
            transform.rotation *= Quaternion.fromAxisAngle(Float3.up, delta.toFloat())

        if (input.keyboardState["ArrowRight"] == true)
            transform.rotation *= Quaternion.fromAxisAngle(Float3.down, delta.toFloat())

    }

}